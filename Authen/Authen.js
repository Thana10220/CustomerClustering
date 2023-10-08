$( document ).ready(function() {

    $("#loginBtn").click(function(){
        var user_name = $("#username").val();
        var password = $("#password").val();

        if(user_name === 'admin'){
            window.location.href = "/Admin";
        }else{
            window.location.href = "/Report";
        }

    });


});