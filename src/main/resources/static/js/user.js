let index ={
    init: function() {
        $("#btn-save").on("click", () => {
            this.save();
        });
    },

    save: function() {
        let data = {
            username : $("#username").val(),
            password : $("#password").val(),
            email : $("#email").val()
        }
//        console.log(data);
        $.ajax({
            type: "POST",
            url: "/api/user",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8"
        }).done(function(res){
            alert("회원가입이 완료되었습니다.")
            location.href = "/"
        }).fail(function(err){
            alert(JSON.stringify(err))
        });
    }
}

index.init();