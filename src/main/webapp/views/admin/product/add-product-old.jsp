<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<c:url value='/static/admin/add-product/style.css'/> ">

<style>
    .error-message {
        color: red;
        font-size: 0.875em;
        margin-top: 5px;
    }
</style>


<div class="content">
    <div class="page-header">
        <div class="page-title">
            <h4>Product Add</h4>
            <h6>Create new product</h6>
        </div>
    </div>

    <div class="product-content product-wrap clearfix product-deatil">
        <div class="row">
            <div class="form-group">
                <h2 class="name">
                    <small>Product name @
                        <div class="row">
                            <div class=" mb-4">
                                <div data-mdb-input-init class="form-outline">
                                    <input type="text" name="firstName" id="productName" class="custom-input form-control form-control-lg" />
                                    <label class="form-label" for="productName">Tên sản phẩm</label>
                                    <div class="error-message" id="productNameError" style="font-size: 12px"></div>
                                </div>
                            </div>
                        </div>
                    </small>
                    <small>Product by @
                        <div class="row">
                            <div class=" mb-4">
                                <div data-mdb-input-init class="form-outline">
                                    <input type="text" name="firstName" id="productBrand" class="custom-input form-control form-control-lg" />
                                    <label class="form-label" for="productBrand">Hãng sản phẩm</label>
                                    <div class="error-message" id="productBrandError" style="font-size: 12px"></div>
                                </div>
                            </div>
                        </div>
                    </small>
                </h2>

            </div>
            </h2>
            <hr />
            <div class="form-group">
                <label>Category</label>
                <select class="select" id="categorySelect">
                    <c:forEach var="item" items="${model}">
                        <option data-id=${item.id} value="${item.code}">${item.name}</option>
                    </c:forEach>
                </select>
                <div class="certified">
                    <ul>
                        <li>
                            <a>Name: <span id="categoryName">--</span></a>
                        </li>
                        <li>
                            <a href="javascript:void(0);">Code: <span id="categoryCode">--</span></a>
                        </li>
                        <input type="hidden" name="category" id="category" class="custom-input form-control form-control-lg" />
                    </ul>
                </div>
            </div>
            <hr />
            <div class="description description-tabs">
                <div class="tab-content" style="height: 400px">
                    <br />
                    <strong>Description Title</strong>
                    <div class="mb-3">
                        <div id="productDescription" style="height: 320px"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Mẫu sản phẩm sử dụng template -->
    <template id="productCardTemplate">
        <div class="product-variant-card card mb-4">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-5 col-sm-12 col-xs-12">
                        <div class="product-image">
                            <div class="item active">
                                <img src="<c:url value='/static/img/product/404.jpg'/>" class="img-responsive" alt="Product Image">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-md-offset-1 col-sm-12 col-xs-12">
                        <div class="row">
                            <div class="col-lg-3 col-sm-6 col-12">
                                <div class="form-group">
                                    <label>Color</label>
                                    <input type="text" placeholder="Color" class="form-control variant-color">
                                    <div class="error-message"></div>
                                </div>
                            </div>
                            <div class="col-lg-3 col-sm-6 col-12">
                                <div class="form-group">
                                    <label>Size</label>
                                    <input type="text" placeholder="Size" class="form-control variant-size">
                                    <div class="error-message"></div>
                                </div>
                            </div>
                            <div class="col-lg-3 col-sm-6 col-12">
                                <div class="form-group">
                                    <label>Quantity</label>
                                    <input type="number" class="form-control variant-quantity">
                                </div>
                            </div>
                            <div class="col-lg-3 col-sm-6 col-12">
                                <div class="form-group">
                                    <label>Price</label>
                                    <input type="text" placeholder="Price" class="form-control variant-price">
                                    <div class="error-message"></div>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label> Product Image</label>
                                    <div class="image-upload">
                                        <input type="file" accept="image/jpeg">
                                        <div class="image-uploads">
                                            <img src="/static/admin/assets/img/icons/upload.svg" alt="img">
                                            <h4>Drag and drop a file to upload</h4>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </template>

    <div id="productVariantsContainer" class="row mt-4"></div>


    <div class="product-content product-wrap clearfix product-deatil">
        <div class="row">
            <div class="col-sm-12 col-md-6 col-lg-6">
                <a href="javascript:void(0);" class="btn btn-secondary btn-lg" onclick="updateProductCards()">Thêm phân loại sản phẩm</a>
            </div>
            <div class="col-sm-12 col-md-6 col-lg-6 d-flex justify-content-end">
                <a id="add-product-btn" href="javascript:void(0);" class="btn btn-primary btn-lg">Xác nhận thêm sản phẩm</a>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
    <link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">


    <script>
        var quill
        $(document).ready(function() {
            quill = new Quill('#productDescription', {
                theme: 'snow'
            });

            $("#categorySelect").change(function() {
                var selectedOption = $(this).find("option:selected");
                var categoryName = selectedOption.text();  // Lấy tên category
                var categoryCode = selectedOption.val();  // Lấy mã category
                var categoryId = selectedOption.data('id');


                // Cập nhật giá trị vào thẻ <span> trong div .certified
                $("#categoryName").text(categoryName);
                $("#categoryCode").text(categoryCode);
                $("#category").val(categoryId);
            });

            updateProductCards()
            $('#add-product-btn').click(addProduct);
        });

        function checkInput () {
            let isValid = true;

            const productName = $('#productName').val();
            if (!productName) {
                $('#productNameError').text('Vui lòng nhập tên sản phẩm.');
                isValid = false;
            } else {
                $('#productNameError').text('');
            }

            const productBrand = $('#productBrand').val();
            if (!productBrand) {
                $('#productBrandError').text('Vui lòng nhập hãng sản phẩm.');
                isValid = false;
            } else {
                $('#productBrandError').text('');
            }

            $('#productVariantsContainer .product-variant-card').each(function(index) {
                const price = $(this).find('.variant-price').val();
                const quantity = $(this).find('.variant-quantity').val();
                const color = $(this).find('.variant-color').val();
                const size = $(this).find('.variant-size').val();

                if (!color) {
                    $(this).find('.variant-color').next('.error-message').text('Vui lòng nhập màu.');
                    isValid = false;
                } else {
                    $(this).find('.variant-color').next('.error-message').text('');
                }

                if (!size) {
                    $(this).find('.variant-size').next('.error-message').text('Vui lòng nhập kích cỡ.');
                    isValid = false;
                } else {
                    $(this).find('.variant-size').next('.error-message').text('');
                }

                if (!quantity || isNaN(quantity) || parseInt(quantity) <= 0) {
                    $(this).find('.variant-quantity').next('.error-message').text('Vui lòng nhập số lượng hợp lệ.');
                    isValid = false;
                } else {
                    $(this).find('.variant-quantity').next('.error-message').text('');
                }

                if (!price || isNaN(price) || parseFloat(price) <= 0) {
                    $(this).find('.variant-price').next('.error-message').text('Vui lòng nhập giá hợp lệ.');
                    isValid = false;
                } else {
                    $(this).find('.variant-price').next('.error-message').text('');
                }
            });
            return isValid
        }


        function updateProductCards() {
            const $container = $("#productVariantsContainer");
            // Lấy mẫu card sản phẩm từ template và nhân bản nó
            const $template = $($("#productCardTemplate").html()).clone();

            // Thêm hiệu ứng fade-in vào template
            $template.addClass("fade-in");

            // Thêm card vào container
            $container.append($template);

            // Thêm hiệu ứng hiển thị chậm
            setTimeout(() => {
                $template.addClass("show");
            }, 100); // 100ms cho hiệu ứng chậm

            $template.find(".image-upload input[type='file']").on("change", function() {
                const file = this.files[0];

                if (file) {
                    const reader = new FileReader();
                    reader.onload = function(e) {
                        // Cập nhật src của img để hiển thị ảnh được chọn
                        $template.find('.product-image .item.active img').attr('src', e.target.result);
                    }
                    reader.readAsDataURL(file);
                }
            });
        }

        function addProduct() {
            if (!checkInput()) return;

            const formData = new FormData();

            var product = {
                name: $('#productName').val(),
                highlight: $('#highlight').is(':checked'),
                status: 'SELLING',
                brand: $('#productBrand').val(),
                description: quill.root.innerHTML,
                category: {
                    id: $('#category').val(),
                },
            };

            formData.append('product.name', product.name);
            formData.append('product.highlight', product.highlight);
            formData.append('product.status', product.status);
            formData.append('product.brand', product.brand);
            formData.append('product.description', product.description);
            formData.append('product.category.id', product.category.id);

            $('#productVariantsContainer .product-variant-card').each(function(index) {
                var variant = {
                    price: parseFloat($(this).find('.variant-price').val()),
                    color: $(this).find('.variant-color').val(),
                    size: $(this).find('.variant-size').val(),
                    quantity: parseInt($(this).find('.variant-quantity').val())
                };

                formData.append(`productVariants[` + index + `].price`, variant.price);
                // formData.append(`productVariants[` + index + `].status`, variant.status);
                formData.append(`productVariants[` + index + `].color`, variant.color);
                formData.append(`productVariants[` + index + `].size`, variant.size);
                formData.append(`productVariants[` + index + `].quantity`, variant.quantity);

                const fileInput = $(this).find(".image-upload input[type='file']")[0];
                if (fileInput && fileInput.files[0]) {
                    formData.append(`productVariants[` + index + `].image`, fileInput.files[0]);
                }
            });

            // Gửi dữ liệu lên server
            $.ajax({
                url: '/api-product',
                type: 'POST',
                data: formData,
                processData: false,  // Không xử lý dữ liệu
                contentType: false,  // Để trình duyệt tự xử lý content-type
                success: function(response) {
                    alert(response);
                },
                error: function(xhr, status, error) {
                    const errorMessage = xhr.responseJSON ? xhr.responseJSON.message : error;
                    alert("Failed to add product: " + errorMessage);
                }
            });
        }

    </script>

</div>