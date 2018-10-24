var article = function () {
    var base = "/6000/article";
    return {
        addPageURL:base+"/articleAdd",
        editPageURL:base+'/articleEdit',
        listPageURL:base+'/articleList',
        deleteURL:base+'/deleteArticle',
        getListURL:base+'/getArticles',
        addURL:base+"/addArticle",
        editURL:base+"/editArticle",
        getURL:base+"/getById",
        toEditPage: function (id) {
            window.location.href = this.editPageURL+'?id=' + id;
        },
        toAddPage: function () {
            window.location.href = this.addPageURL;
        },
        deleteById: function (id) {
            $.confirm({
                theme: 'supervan',
                title: '删除!',
                content: '是否确认删除？',
                buttons: {
                    "确认": function () {
                        $.ajax({
                            async: true,
                            dataType: "json",
                            url: article.deleteURL,
                            data: {"id": id},
                            success: function (data) {
                                if (data == true) {
                                    $.toast({
                                        heading: '删除',
                                        text: '成功',
                                        showHideTransition: 'fade',
                                        position: "top-right",
                                        icon: 'success'
                                    });
                                    $('#dg').datagrid('reload');
                                } else {
                                    $.toast({
                                        heading: '删除',
                                        text: '失败',
                                        showHideTransition: 'fade',
                                        position: "top-right",
                                        icon: 'error'
                                    })
                                }
                            },
                            error: function (xhr, status, error) {
                                $.toast({
                                    heading: '删除',
                                    text: "失败，错误" + error.toString(),
                                    showHideTransition: 'fade',
                                    position: "top-right",
                                    icon: 'error'
                                })
                            }
                        });
                        $('#dg').datagrid('reload');
                    },
                    "取消": function () {
                    }
                }
            });
        },
        initList: function () {
            $('#dg').datagrid({
                url:this.getListURL,
                rownumbers:true,
                pagination:true,
                pageSize:20,
                singleSelect:true,
                fitColumns:true,
                columns:[[
                    {field:'_operate',title:'操作',width:'10%',align:"center",halign:"center",formatter:this.onActionRenderer},
                    {field:'id',title:'文章ID',width:'45%',align:"center",halign:"center"},
                    {field:'title',title:'标题',width:'45%',align:"center",halign:"center"},
                ]],
                rowStyler: function(index,row){
                    return 'font-weight: bold;width:100%;';
                }
            });


        },
        initEdit:function(id){
            var editor = UE.getEditor('ue_content',{
                //两倍iphone6宽为宽
                initialFrameWidth:750,//初始化宽度
                //一倍iphone6高为高
                initialFrameHeight:667,//初始化高度
                maximumWords:4000,//最大字数
                savePath:['article'],
                pictureLocation:'article'
            });
            this.formValidInit(this.editURL);
            this.renderEdit(id);

        },
        initAdd:function(){
            var editor = UE.getEditor('ue_content',{
                //两倍iphone6宽为宽
                initialFrameWidth:750,//初始化宽度
                //一倍iphone6高为高
                initialFrameHeight:667,//初始化高度
                maximumWords:4000,//最大字数
                savePath:['article'],
                pictureLocation:'article'
            });
            this.formValidInit(this.addURL);
        },
        renderEdit : function (id) {
            $.ajax({
                async: false,
                dataType: "json",
                url: this.getURL,
                data:{"id":id},
                success: function (data) {
                    $("[name='title']").val(data.title);
                    UE.getEditor('ue_content').ready(function () {
                        UE.getEditor('ue_content').setContent(data.content);
                    });
                }
            });
        },
        getFormData:function () {
            // //基本信息
            var formInfo = $("#tform").serializeObject();
            formInfo.content = UE.getEditor('ue_content').getContent();
            return formInfo;
        },
        formValidInit : function (url) {
            $("#tform").validate({
                submitHandler:function(form){
                    var acontent = UE.getEditor('ue_content').getContent();
                    if(!acontent || acontent == null || acontent == ""){
                        $.toast({
                            heading: '保存',
                            text: '内容不能为空',
                            showHideTransition: 'fade',
                            position:"top-right",
                            icon: 'error'
                        })
                        return false;
                    }
                    $.ajax({
                        async:true,
                        dataType:"json",
                        url:url,
                        method:"POST",
                        data:article.getFormData(),
                        success: function (data) {
                            if(data == true){
                                $.toast({
                                    heading: '保存',
                                    text: '成功',
                                    showHideTransition: 'fade',
                                    position:"top-right",
                                    icon: 'success'
                                });
                                setInterval('window.location.href=article.listPageURL',2000);
                            }else{
                                $.toast({
                                    heading: '保存',
                                    text: '失败',
                                    showHideTransition: 'fade',
                                    position:"top-right",
                                    icon: 'error'
                                })
                            }
                        },
                        error:function(xhr,status,error){
                            $.toast({
                                heading: '保存',
                                text: "失败，错误"+error.toString(),
                                showHideTransition: 'fade',
                                position:"top-right",
                                icon: 'error'
                            })
                        }
                    });
                }

            });
        },
        search: function () {
            var title = $("#keyWord").val();
            var id = $("#keyWordId").val();
            $('#dg').datagrid('load',{"title": title, "id": id});
        },

        onActionRenderer: function (value,row,index) {

            var s =
                ' <a class="icon-pencil " href="javascript:article.toEditPage(\'' + row.id + '\');" >编辑</a>'
                + ' <a class=" icon-remove  " href="javascript:article.deleteById(\'' + row.id + '\');">删除</a>';

            return s;
        },
        resetForm: function resetForm() {
            $("#tform")[0].reset();
            UE.getEditor('ue_content').execCommand('cleardoc');
        }

    }
}();