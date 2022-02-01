let index ={
    init: function() {
        $("#btn-save").on("click", () => {
            this.save();
        });
        $("#btn-delete").on("click",()=>{
            this.delete();
        });
        $("#btn-update").on("click",()=>{
            this.update();
        });
        $("#btn-reply-save").on("click",()=>{
        this.replySave();
        });
    },

        save: function() {
            let data = {
                title : $("#title").val(),
                content : $("#content").val()
            }
            $.ajax({
                type: "POST",
                url: "/board/save",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8"
            }).done(function(res){
                alert("글쓰기가 완료되었습니다.")
                location.href = "/"
            }).fail(function(err){
                alert(JSON.stringify(err))
            });
        },
        
        delete: function() {
            let id = $("#id").text();
            $.ajax({
                type: "DELETE",
                url: "/board/delete/" + id,
                contentType: "application/json; charset=utf-8"
            }).done(function(res){
                alert("글삭제가 완료되었습니다.")
                location.href = "/"
            }).fail(function(err){
                alert(JSON.stringify(err))
            });
        },

        update: function() {
            let id = $("#id").val();
            let data = {
                title : $("#title").val(),
                content : $("#content").val()
            }
            $.ajax({
                type: "PUT",
                url: "/board/" + id ,
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8"
            }).done(function(res){
                alert("글수정이 완료되었습니다.")
                location.href = "/"
            }).fail(function(err){
                alert(JSON.stringify(err))
            });
        },
        replySave: function() {
            let data = {
                userId : $("$userId").val(),
                boardId : $("$boardId").val(),
                content : $("#reply-content").val()
            }
            $.ajax({
                type: "POST",
                url: "/board/${data.boardId}/reply",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8"
            }).done(function(res){
                alert("댓글작성이 완료되었습니다.")
                location.href = `/board/${data.boardId}`
            }).fail(function(err){
                alert(JSON.stringify(err))
            });
        },
}

index.init();
