var productCategory = function () {
    var base = "/1000/productCategory";
    return {
        addPageURL:base+"/foodAdd",
        editPageURL:base+'/foodEdit',
        listPageURL:base+'/productCategoryList',
        deleteURL:base+'/productCategoryDelete',
        getListURL:base+"/getFoods",
        addURL:base+"/productCategoryAddForm",
        editURL:base+"/CategoryEdit",
        getURL:base+"/getById",
        foodListURL:base+"/getAllFood",
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
                            url: productCategory.deleteURL,
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
                        window.location.href = productCategory.listPageURL;
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
                    {field:'_operate',title:'操作',width:'10%',align:"center",halign:"center"},
                    {field:'id',title:'id',width:'10%',align:"center",halign:"center",hidden:true},
                    {field:'username',title:'用户名',width:'10%',align:"center",halign:"center"},
                    {field:'nickname',title:'昵称',width:'10%',align:"center",halign:"center"},
                    {field:'gender',title:'性别',width:'10%',align:"center",halign:"center",formatter:this.onGenderRender},
                    {field:'phonenumber',title:'手机号',width:'30%',align:"center",halign:"center"},
                    {field:'email',title:'邮箱',width:'10%',align:"center",halign:"center"},
                    {field:'birthdayStr',title:'生日',width:'10%',align:"center",halign:"center"},
                ]],
                rowStyler: function(index,row){
                    return 'font-weight: bold;width:100%;';
                }
            });


        },
        initEdit:function(id){
            this.formValidInit(this.editURL);
            this.renderFood(id);
        },
        initAdd:function(){
            this.formValidInit(this.addURL);
        },
        renderFood : function (id) {
            $.ajax({
                async: false,
                dataType: "json",
                url: this.getURL,
                data:{"id":id},
                success: function (data) {
                    $("[name='name']").val(data.name);
                    customUploader.showImg(data.picIdUrl,data.picId);
                }
            });
        },
        getFoodList: function () {
            foodList = [];
            $.ajax({
                async: false,
                dataType: "json",
                url: this.foodListURL,
                success: function (data) {
                    foodList = data;
                }
            });
            return foodList;
        },
        getFormData:function () {
            // //基本信息
            var formInfo = $("#tform").serializeObject();
            return formInfo;
        },
        formValidInit : function (url) {
            $("#tform").validate({
                submitHandler:function(form){
                    $.ajax({
                        async:true,
                        contentType: "application/json; charset=utf-8",
                        dataType:"json",
                        url:url,
                        method:"POST",
                        data:JSON.stringify(productCategory.getFormData()),
                        success: function (data) {
                            if(data == true){
                                $.toast({
                                    heading: '保存',
                                    text: '成功',
                                    showHideTransition: 'fade',
                                    position:"top-right",
                                    icon: 'success'
                                });
                                setInterval('window.location.href=productCategory.listPageURL',1000);
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
            var name = $("#keyWord").val();
            var type = $("#sel_type").val();
            $('#dg').datagrid('load',{"name": name, "type": type});
        },


        onActionRenderer: function (value,row,index) {

            var s =
                ' <a class="icon-pencil " href="javascript:food.toEditPage(\'' + row.id + '\');" >编辑</a>'
                + ' <a class=" icon-remove  " href="javascript:food.deleteById(\'' + row.id + '\');">删除</a>';

            return s;
        },


        resetForm: function resetForm() {
            $("#tform")[0].reset();
            customUploader.removeAllFiles();
        }

    }
}();