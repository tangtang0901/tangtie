var member = function () {
    var base = "/1000/food";
    return {
        addPageURL:base+"/foodAdd",
        editPageURL:base+'/foodEdit',
        listPageURL:base+'/foodList',
        deleteURL:base+'/deleteFood',
        getListURL:base+"/getFoods",
        addURL:base+"/addFood",
        editURL:base+"/editFood",
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
                            url: food.deleteURL,
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
                    $("[name='type']").val(data.type);
                    $("[name='nature']").val(data.nature);
                    $("[name='ph']").val(data.ph);
                    $("[name='minUnit']").val(data.minUnit);
                    $("[name='name']").val(data.name);
                    $("[name='avoidance']").val(data.avoidance);
                    $("[name='fit']").val(data.fit);
                    customUploader.showImg(data.pic,data.picId);
                    if(data.avoidanceIds != null){
                        for(avoidanceIndex in data.avoidanceIds){
                            food.addFoodRelateLine(1,data.avoidanceIds[avoidanceIndex]);
                        }
                    }
                    if(data.fitIds != null){
                        for(fitIndex in data.fitIds){
                            food.addFoodRelateLine(2,data.fitIds[fitIndex]);
                        }
                    }
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
            console.log(typeof formInfo.avoidanceIds);
            if((typeof formInfo.avoidanceIds) == 'string'){
                formInfo.avoidanceIds = [formInfo.avoidanceIds];
            }
            console.log(typeof formInfo.fitIds);
            if((typeof formInfo.fitIds) == 'string'){
                formInfo.fitIds = [formInfo.fitIds];
            }


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
                                setInterval('window.location.href=food.listPageURL',2000);
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

        onTypeRender: function (value,row,index) {
            if (value == 1) return "主食";
            if (value == 2) return "蔬菜";
            if (value == 3) return "肉类";
            if (value == 4) return "植物蛋白";
            if (value == 5) return "其他";
        },
        onGenderRender: function (value,row,index) {
            if (value == 1) return "男";
            if (value == 0) return "女";
        },
        onNatureRender: function (value,row,index) {
            if (value == 1) return "性寒";
            if (value == 2) return "性凉";
            if (value == 3) return "中平";
            if (value == 4) return "性温";
            if (value == 5) return "性热";
        },
        onPhRender: function (value,row,index) {
            if (value == 1) return "酸性";
            if (value == 2) return "碱性";
        },
        onMinUnitRender: function (value,row,index) {
            if (value == 0) return "10g";
            if (value == 1) return "15g";
            if (value == 2) return "50g";
            if (value == 3) return "100g";
        },

        onActionRenderer: function (value,row,index) {

            var s =
                ' <a class="icon-pencil " href="javascript:food.toEditPage(\'' + row.id + '\');" >编辑</a>'
                + ' <a class=" icon-remove  " href="javascript:food.deleteById(\'' + row.id + '\');">删除</a>';

            return s;
        },
        //新增相克食材
        addFoodRelateLine: function addFoodRelateLine(type,value) {
            var labletitle = "";
            var inputname = "";
            var groupId = "";
            if (type == 1) {
                labletitle = "相克食材";
                inputname = "avoidanceIds";
                groupId = "#addAvoidanceGroup";
            }
            if (type == 2) {
                labletitle = "相宜食材";
                inputname = "fitIds";
                groupId = "#addFitGroup";
            }
            var c_group = $("<div>", {class: "control-group", removeFlag: ""});
            var c_lable = $("<lable>", {class: "control-label"});
            var c_close_btn = $("<button>", {class: "close", "aria-label": "Close"});
            c_lable.append(labletitle);
            c_lable.append(c_close_btn);
            var c_controls = $("<div>", {class: "controls"});
            var c_input = $("<input>", {class: "span6 select2", name: inputname});
            c_controls.append(c_input);
            c_group.append(c_lable);
            c_group.append(c_controls);
            $(groupId).append(c_group);
            //绑定下拉
            foodList = this.getFoodList();
            c_input.select2({
                    placeholder: "请输入食材名称",
                    minimumInputLength: 0,
                    data: foodList
                }
            );
            c_input.select2("val",value);
            c_close_btn.click(function () {
                c_group.remove();
            });
        },
        resetForm: function resetForm() {
            $("#tform")[0].reset();
            $("[removeFlag]").remove();
            customUploader.removeAllFiles();
        },
        resetElements:function () {
            $("[name='element']").val(0);
        }

    }
}();