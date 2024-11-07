<%--
  Created by IntelliJ IDEA.
  User: quang
  Date: 11/6/2024
  Time: 10:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create-Product</title>
</head>
<body>
<form id = "formSubmit" enctype="multipart/form-data">
    <div class="col-lg-8 vstack gap-4">
        <!-- Card START -->
        <div class="card card-body">
            <!-- Post input -->
            <div class="w-100" >
<%--                <input type="hidden" id="currentImagePath" name="currentImagePath" value="${postModel.imagePath}" />--%>
<%--                <input type="hidden" id="imageAction" name="imageAction" value="keep" />--%>
                <textarea  class="form-control pe-4 border-0" rows="1" maxlength="100" data-autoresize=""
                           name="name" placeholder="name...">${postModel.title}</textarea>
                <textarea maxlength="1200" class="form-control pe-4 border-0" rows="2" data-autoresize=""
                          name="description" placeholder="description...">${postModel.content}</textarea>
                <div>

                    <div>
                            <input type="file" id="input-file-to-destroy" class="dropify"
                                   data-default-file="/post-image-api?path=${postModel.imagePath}" data-max-file-size="25M"
                                   data-max-height="3000" name="image" src="" />
                    </div>

                        <input type="button" id="btnSubmitPost" class="btn btn-sm btn-info" style="margin:5px; float:right" value = "Post" />
                    <input type="hidden" id ="id" name="id" value=""/>
                </div>
            </div>
            <!-- Share feed toolbar END -->
        </div>
    </div>

    <div>
        <input type="text" name="productVariants[0].id" value="1" />
        <input type="text" name="productVariants[0].price" value="100" />
        <input type="text" name="productVariants[0].status" value="AVAILABLE" />
        <input type="text" name="productVariants[0].color" value="Red" />
        <input type="text" name="productVariants[0].size" value="M" />
        <input type="text" name="productVariants[0].quantity" value="50" />
    </div>
    <div>
        <input type="text" name="productVariants[1].id" value="2" />
        <input type="text" name="productVariants[1].price" value="120" />
        <input type="text" name="productVariants[1].status" value="AVAILABLE" />
        <input type="text" name="productVariants[1].color" value="Blue" />
        <input type="text" name="productVariants[1].size" value="L" />
        <input type="text" name="productVariants[1].quantity" value="30" />
    </div>

</form>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $('#btnSubmitPost').click(function(e){
        // alert("asssss")
        e.preventDefault();
        let formData = new FormData($('#formSubmit')[0]);

        let id = $('#id').val();
        if(id ===""){

            addPost(formData);
        }

        else{
            updatePost(formData);
        }
        function addPost(formData){
            $.ajax({
                url : '/api-product',
                type : 'post',
                data : formData,
                processData: false,
                contentType: false,
                // dataType : 'json',
                success : function(result){
                    if(result.status ==="success"){
                        window.location.href = "/profile?alert=success&message=create-post-success";
                    }else{
                        $('#result-message').removeClass("alert-success").addClass("alert-danger")
                            .html('<i class="ace-icon fa fa-exclamation-triangle red bigger-130"></i> ' + result.message).show();
                    }
                },
                error: function (){
                    $('#result-message').removeClass("alert-success").addClass("alert-danger")
                        .html('<i class="ace-icon fa fa-exclamation-triangle red bigger-130"></i> ' +
                            '<div>Đã có lỗi xảy ra, vui lòng thử lại vào thời điểm khác.</div>' +
                            '<a href="/"><b>Trở về trang chủ</b></a>').show();
                },
            })

        }
        function updatePost(formData){
            $.ajax({
                url : '/edit-post',
                type : 'put',
                data : formData,
                processData: false,
                contentType: false,
                success : function(result){
                    if(result.status ==="success"){
                        window.location.href = "/profile?alert=success&message=edit-post-success";
                    }else{
                        $('#result-message').removeClass("alert-success").addClass("alert-danger")
                            .html('<i class="ace-icon fa fa-exclamation-triangle red bigger-130"></i> ' + result.message).show();
                    }
                },
                error: function (){
                    $('#result-message').removeClass("alert-success").addClass("alert-danger")
                        .html('<i class="ace-icon fa fa-exclamation-triangle red bigger-130"></i> ' +
                            '<div>Đã có lỗi xảy ra, vui lòng thử lại vào thời điểm khác.</div>' +
                            '<a href="/"><b>Trở về trang chủ</b></a>').show();
                },
            })

        }

    });
</script>

</body>
</html>
