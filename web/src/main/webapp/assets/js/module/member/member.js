var member = function () {
    var base = "/5000/member";
    return {
        addPageURL:base+"/memberAdd",
        editPageURL:base+'/memberEdit',
        listPageURL:base+'/memberList',
        deleteURL:base+'/enable',
        enableURL:base+'/enable',
        disableURL:base+'/disable',
        getListURL:base+"/getMembers",
        addURL:base+"/addMember",
        editURL:base+"/updateMember",
        getURL:base+"/getById",
        checkUsernameURL:base+"/checkUsername",
        checkUsernameURL:base+"/checkPhone",
        changePasswordURL:base+"/changePassword",
        toEditPage: function (id) {
            window.location.href = this.editPageURL+'?id=' + id;
        },
        toAddPage: function () {
            window.location.href = this.addPageURL;
        },
        changePassword: function (id) {
            $.confirm({
                theme: 'supervan',
                title: '新密码',
                content: '<input id="newPasw"/>',
                buttons: {
                    "确认": function () {
                        //判断是否符合密码要求
                        var passw = $("#newPasw").val();
                        var reg = /^\w{6,20}$/;
                        if(reg.test(passw)){
                            $.ajax({
                                async: true,
                                dataType: "json",
                                url: member.changePasswordURL,
                                data: {"id": id,"password":passw},
                                success: function (data) {
                                    if (data == true) {
                                        $.toast({
                                            heading: '修改密码',
                                            text: '成功',
                                            showHideTransition: 'fade',
                                            position: "top-right",
                                            icon: 'success'
                                        });
                                    } else {
                                        $.toast({
                                            heading: '修改密码',
                                            text: '失败',
                                            showHideTransition: 'fade',
                                            position: "top-right",
                                            icon: 'error'
                                        })
                                    }
                                },
                                error: function (xhr, status, error) {
                                    $.toast({
                                        heading: '修改密码',
                                        text: "失败，错误" + error.toString(),
                                        showHideTransition: 'fade',
                                        position: "top-right",
                                        icon: 'error'
                                    })
                                }
                            });
                        }else {
                            $.toast({
                                heading: '修改失败',
                                text: '"只允许6-20位英文字母、数字和下画线"',
                                showHideTransition: 'fade',
                                position: "top-right",
                                icon: 'error'
                            })
                        }

                    },
                    "取消": function () {
                    }
                }
            });
        },
        enable: function (id) {
            $.ajax({
                async: true,
                dataType: "json",
                url: member.enableURL,
                data: {"id": id},
                success: function (data) {
                    if (data == true) {
                        $.toast({
                            heading: '启用',
                            text: '成功',
                            showHideTransition: 'fade',
                            position: "top-right",
                            icon: 'success'
                        });
                        $('#dg').datagrid('reload');
                    } else {
                        $.toast({
                            heading: '启用',
                            text: '失败',
                            showHideTransition: 'fade',
                            position: "top-right",
                            icon: 'error'
                        })
                    }
                },
                error: function (xhr, status, error) {
                    $.toast({
                        heading: '启用',
                        text: "失败，错误" + error.toString(),
                        showHideTransition: 'fade',
                        position: "top-right",
                        icon: 'error'
                    })
                }
            });
            setTimeout("$('#dg').datagrid('reload')",1000);
        },
        disable: function (id) {
            $.ajax({
                async: true,
                dataType: "json",
                url: member.disableURL,
                data: {"id": id},
                success: function (data) {
                    if (data == true) {
                        $.toast({
                            heading: '禁用',
                            text: '成功',
                            showHideTransition: 'fade',
                            position: "top-right",
                            icon: 'success'
                        });
                        $('#dg').datagrid('reload');
                    } else {
                        $.toast({
                            heading: '禁用',
                            text: '失败',
                            showHideTransition: 'fade',
                            position: "top-right",
                            icon: 'error'
                        })
                    }
                },
                error: function (xhr, status, error) {
                    $.toast({
                        heading: '禁用',
                        text: "失败，错误" + error.toString(),
                        showHideTransition: 'fade',
                        position: "top-right",
                        icon: 'error'
                    })
                }
            });
            setTimeout("$('#dg').datagrid('reload')",1000);
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
                    {field:'_operate',title:'操作',width:'250',align:"center",halign:"center",formatter:this.onActionRenderer},
                    {field:'id',title:'id',width:'100',align:"center",halign:"center",hidden:true},
                    {field:'username',title:'用户名',width:'100',align:"center",halign:"center"},
                    {field:'nickname',title:'昵称',width:'100',align:"center",halign:"center"},
                    {field:'gender',title:'性别',width:'100',align:"center",halign:"center",formatter:this.onGenderRender},
                    {field:'phonenumber',title:'手机号',width:'100',align:"center",halign:"center"},
                    {field:'email',title:'邮箱',width:'100',align:"center",halign:"center"},
                    {field:'birthdayStr',title:'生日',width:'150',align:"center",halign:"center"},
                    {field:'isEnable',title:'启用状态',width:'100',align:"center",halign:"center",formatter:this.onStatusRender},
                ]],
                rowStyler: function(index,row){
                    return 'font-weight: bold;width:100%;';
                }
            });


        },
        initEdit:function(id){
            $('#birthdayDatetimepicker').datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose:true,
                startView:"year",
                minView:"month"
            });
            this.formValidInit(this.editURL);
            this.renderMember(id);

        },
        renderMember : function (id) {
            $.ajax({
                async: false,
                dataType: "json",
                url: this.getURL,
                data:{"id":id},
                success: function (data) {
                    $("[name='username']").val(data.username);
                    $("[name='nickname']").val(data.nickname);
                    $("[name='birthdayStr']").val(data.birthdayStr);
                    $("[name='gender']").val(data.gender);
                    $("[name='phonenumber']").val(data.phonenumber);
                    $("[name='email']").val(data.email);
                    customUploader.showImg(data.avatarUrl,data.avatar);
                }
            });
        },
        initAdd:function(){
            $('#birthdayDatetimepicker').datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose:true,
                startView:"year",
                minView:"month"
            });
            this.formValidInit(this.addURL);
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
                        data:JSON.stringify(member.getFormData()),
                        success: function (data) {
                            if(data == true){
                                $.toast({
                                    heading: '保存',
                                    text: '成功',
                                    showHideTransition: 'fade',
                                    position:"top-right",
                                    icon: 'success'
                                });
                                setTimeout('window.location.href=member.listPageURL',2000);
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
                    console.log(JSON.stringify(member.getFormData()));
                }

            });
        },
        search: function () {
            var name = $("#keyWord").val();
            $('#dg').datagrid('load',{"name": name});
        },

        onGenderRender: function (value,row,index) {
            if (value == 1) return "男";
            if (value == 0) return "女";
        },
        onStatusRender: function (value,row,index) {
            if (value == true) return "启用";
            if (value == false) return "禁用";
        },

        onActionRenderer: function (value,row,index) {

            var s =
                ' <a class="icon-pencil " href="javascript:member.toEditPage(\'' + row.id + '\');" >编辑</a>'
                + ' <a class=" icon-asterisk  " href="javascript:member.changePassword(\'' + row.id + '\');">修改密码</a>'
                + ' <a class=" icon-star  " href="javascript:member.modifyPoint(\'' + row.id + '\');">修改唐豆</a>'
                + ' <a class=" icon-play  " href="javascript:member.enable(\'' + row.id + '\');">启用</a>'
                + ' <a class=" icon-stop  " href="javascript:member.disable(\'' + row.id + '\');">禁用</a>';

            return s;
        },
        resetForm: function resetForm() {
            $("#tform")[0].reset();
            customUploader.removeAllFiles();
        }

    }
}();