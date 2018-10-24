var page = function () {
    return {
        getPages:'/3000/page/pageList',
        deletePage:'/3000/page/deletePage',
        addPage:"/3000/page/addPage",
        editPage:"/3000/page/editPage",
        pageEdit:"/3000/page/pageEdit",
        deleteFloor:'/3000/page/deleteFloor',
        addFloor:"/3000/page/addFloor",
        editFloor:"/3000/page/editFloor",
        floorEdit:"/3000/page/floorEdit",
        deleteElement:'/3000/page/deleteElement',
        addElement:"/3000/page/addElement",
        editElement:"/3000/page/editElement",
        elementEdit:"/3000/page/elementEdit",
        listGrid1:{},
        listGrid2:{},
        listGrid3:{},
        initList: function () {
            mini.parse();
            var grid = mini.get("datagrid1");
            page.listGrid1 = grid;
            grid.load();

        },
        onActionRenderer: function onActionRenderer(e) {
            var grid = e.sender;
            var record = e.record;
            var id = record.id;

            var s =
                ' <a class="icon-adjust " href="javascript:page.getFloors(\'' + id + '\');" >楼层</a>'+
                ' <a class="icon-pencil " href="javascript:page.toEditPage(\'' + id + '\');" >编辑</a>'
                + ' <a class=" icon-remove  " href="javascript:page.deleteById(\'' + id + '\');">删除</a>';

            return s;
        },
        onActionRenderer2: function onActionRenderer(e) {
            var grid = e.sender;
            var record = e.record;
            var id = record.id;

            var s =
                ' <a class="icon-adjust " href="javascript:page.getElements(\'' + id + '\');" >元素</a>'+
                ' <a class="icon-pencil " href="javascript:page.toEditFloor(\'' + id + '\');" >编辑</a>'
                + ' <a class=" icon-remove  " href="javascript:page.deleteById(\'' + id + '\');">删除</a>';

            return s;
        },
        onActionRenderer3: function onActionRenderer(e) {
            var grid = e.sender;
            var record = e.record;
            var id = record.id;

            var s =
                ' <a class="icon-pencil " href="javascript:page.toEditElement(\'' + id + '\');" >编辑</a>'
                + ' <a class=" icon-remove  " href="javascript:page.deleteById(\'' + id + '\');">删除</a>';

            return s;
        },
        getFloors: function (id) {
            mini.parse();
            var grid = mini.get("datagrid2");
            page.listGrid2 = grid;
            grid.load({"pageId": id});
            $("#floor_add").attr("href","/3000/page/floorAdd?pageId="+id);
        },
        getElements: function (id) {
            mini.parse();
            var grid = mini.get("datagrid3");
            page.listGrid3 = grid;
            grid.load({"floorId": id});
        },
        toEditPage: function (id) {
            window.location.href = this.pageEdit+'?id=' + id
        },
        toEditFloor: function (id) {
            window.location.href = this.floorEdit+'?id=' + id
        },
        toEditElement: function (id) {
            window.location.href = this.elementEdit+'?id=' + id
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
                            url: page.deleteURL,
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
                                    page.listGrid.reload();
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
                        mini.get("datagrid1").reload();
                    },
                    "取消": function () {
                    }
                }
            });
        },

        formValidInit : function (url) {
            $("#tform").validate({
                submitHandler:function(){
                    $.ajax({
                        async:true,
                        dataType:"json",
                        url:url,
                        method:"POST",
                        data: page.getFormData(),
                        success: function (data) {
                            if(data.result == true){
                                $.toast({
                                    heading: '保存',
                                    text: '成功',
                                    showHideTransition: 'fade',
                                    position:"top-right",
                                    icon: 'success'
                                });
                                setInterval('window.location.href = page.getPages',2000);
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

        initPageEdit:function(){
            this.formValidInit(this.editPage);
        },

        initPageAdd:function(){
            this.formValidInit(this.addPage);
        },

        initFloorEdit:function(){
            this.formValidInit(this.editFloor);

        },

        initFloorAdd:function(id){
            this.formValidInit(this.addFloor+"?pageId="+id);
        },

        initElementEdit:function(){
            this.formValidInit(this.editElement);

        },

        initElementAdd:function(){
            this.formValidInit(this.addElement);
        },

        getFormData:function () {
            // //基本信息
            var formInfo = $("#tform").serializeObject();
            return formInfo;
        },
    }
}();