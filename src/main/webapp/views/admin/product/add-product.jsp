<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<c:url value='/static/admin/add-product/style.css'/> ">

<style>
    .error-message {
        color: red;
        font-size: 0.875em;
        margin-top: 5px;
    }
    .custom-fieldset {
        padding: 15px;
        position: relative;
        margin-top: 10px;
        background-color: #f9f9f9; /* Màu nền của vùng viền */
    }
    .error-message {
        color: red;
        font-size: 12px;
        margin-top: 5px;
    }

    .custom-fieldset {
        padding: 15px;
        position: relative;
        margin-top: 10px;
        background-color: white;
    }

    .custom-legend {
        font-size: 12px;
        font-weight: bold;
        background-color: #f9f9f9;
        position: absolute;
        top: -10px;
        left: 20px;
    }

    .d-flex {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }

    .form-control {
        border-radius: 4px;
        padding: 8px;
        box-sizing: border-box;
    }

    .product-image {
        display: flex;
        justify-content: center; /* Căn giữa ảnh */
        align-items: center;
        height: 360px; /* Chiều cao tối đa cho container ảnh */
        overflow: hidden; /* Cắt bớt ảnh nếu vượt quá container */
    }

    .product-img {
        max-width: 100%; /* Đảm bảo ảnh không vượt quá chiều rộng container */
        max-height: 100%; /* Đảm bảo ảnh không vượt quá chiều cao container */
        object-fit: contain; /* Giữ tỉ lệ ảnh trong vùng hiển thị */
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
                                    <div class="error-message text-danger" id="productNameError" style="font-size: 12px"></div>
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
                                    <div class="error-message text-danger" id="productBrandError" style="font-size: 12px"></div>
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
                        <input type="hidden" name="category" id="category"
                                <c:if test="${not empty model}">
                                    value="${model[0].id}"
                                </c:if>
                               class="custom-input form-control form-control-lg" />
                    </ul>
                </div>

            </div>
            <hr />
            <div class="description description-tabs" style="margin-bottom: 10px">
                <div class="tab-content" style="height: 400px">
                    <br />
                    <strong>Description Title</strong>
                    <div class="mb-3">
                        <div id="productDescription" style="height: 320px"></div>
                    </div>
                </div>
            </div>
            <hr/>
            <div class="size-table">
                <br />
                <strong>Up ảnh bảng size</strong>
                    <div class="product-image">
                        <div class="item active">
                            <img src="<c:url value='/static/img/product/404.jpg'/>" class="img-responsive product-img" alt="Product Image" id = "previewSizeTable">
                        </div>
                    </div>
                    <div class="form-group" style="max-width: 360px; max-height: 200px">
                        <label>Product Image</label>
                        <div class="image-upload">
                            <input type="file" accept="image/jpeg" id="imageInputSizeTable">
                            <div class="image-uploads">
                                <img src="/static/admin/assets/img/icons/upload.svg" alt="img">
                                <h4>Drag and drop a file to upload</h4>
                            </div>
                        </div>
                    </div>
            </div>
<%--            </div>--%>
            <hr/>
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
                    <div class="col-md-7 col-md-offset-1 col-sm-12 col-xs-12 row">
                        <div class="col">
                            <div class="input-group input-group-sm mb-3" id="input-name">
                                <div class="input-group-prepend">
                                    <span class="input-group-text btn-primary" id="inputGroup-sizing-sm" style="color: white;" >Color</span>
                                </div>
                                <input type="text" class="form-control variant-color" name="name" aria-label="Small" aria-describedby="inputGroup-sizing-sm" value="" style="max-width: 150px;">
                                <div class="error-message text-danger"></div>
                            </div>
                        </div>
                        <div class="w-100"></div>
                        <div class="row size-container">
                            <!-- Các ô Size, Quantity và Price sẽ được thêm vào đây -->
                        </div>
                        <button type="button" class="col align-items-center btn btn-primary add-size-btn mt-5" style="max-width: 200px; max-height: 40px;" onclick="addSize(this)">Thêm Size</button>
                        <div class="w-100"></div>
                        <div class="col" style="margin-top: 20px;">
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
                        <div class="mt-3 d-flex justify-content-end">
                            <button class="btn btn-danger" onclick="removeProductVariantCard(this)" >Hủy</button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </template>


    <template id="sizeQuantityPriceTemplate">
        <fieldset class="custom-fieldset border-bottom" style="max-height: 80px; margin: 10px">
            <legend class="custom-legend">Phân loại hàng</legend>
            <div class="d-flex align-items-center mb-2 single-size-row">

                <div class="input-group mb-3">
                    <input
                            type="text"
                            class="form-control variant-size"
                            placeholder="Size"
                            aria-label="Size"
                    />
                    <span class="input-group-text">@</span>
                    <input
                            type="number"
                            class="form-control variant-quantity"
                            placeholder="Quantity"
                            aria-label="Quantity"
                    />
                    <span class="input-group-text">@</span>
                    <input
                            type="text"
                            class="form-control variant-price"
                            placeholder="Price"
                            aria-label="Price"
                    />
                    <div class="d-flex align-items-center justify-content-center">
                        <button type="button" class="btn btn-danger btn-sm remove-row-btn ms-2" style="font-size: 0.8rem;" onclick="removeSizeRow(this)">🗑</button>
                    </div>
                </div>

<%--                <!-- Ô Size lớn hơn -->--%>
<%--                <div class="flex-fill me-2" style="max-width: 40%; position: relative;">--%>
<%--                    <input type="text" name="variantSize" placeholder="Size" class="form-control variant-size">--%>
<%--                    <div class="error-message text-danger" style="position: absolute; bottom: -18px; left: 0; font-size: 12px;"></div>--%>
<%--                </div>--%>

<%--                <!-- Ô Quantity nhỏ hơn -->--%>
<%--                <div class="flex-fill me-2" style="max-width: 20%; position: relative;">--%>
<%--                    <input type="number" name="variantQuantity" placeholder="Quantity" class="form-control variant-quantity">--%>
<%--                    <div class="error-message text-danger" style="position: absolute; bottom: -18px; left: 0; font-size: 12px;"></div>--%>
<%--                </div>--%>

<%--                <!-- Ô Price lớn hơn -->--%>
<%--                <div class="flex-fill me-2" style="max-width: 40%; position: relative;">--%>
<%--                    <input type="text" name="variantPrice" placeholder="Price" class="form-control variant-price">--%>
<%--                    <div class="error-message text-danger" style="position: absolute; bottom: -18px; left: 0; font-size: 12px;"></div>--%>
<%--                </div>--%>

                <!-- Nút Xóa -->
<%--                <div class="d-flex align-items-center justify-content-center">--%>
<%--                    <button type="button" class="btn btn-danger btn-sm remove-row-btn ms-2" style="font-size: 0.8rem;" onclick="removeSizeRow(this)">🗑</button>--%>
<%--                </div>--%>
            </div>
        </fieldset>
    </template>





    <div id="productVariantsContainer" class="row mt-4">
        <br>
        <strong>Chi tiết sản phẩm </strong>
    </div>


    <div class="product-content product-wrap clearfix product-deatil">
        <div class="row">
            <!-- Confirm and Cancel Buttons -->
            <div class="col d-flex justify-content-end align-items-center">
                <!-- Cancel Button -->
                <button id="cancel-button" class="btn btn-secondary me-2" onclick="updateProductCards()">Thêm phân loại sản phẩm</button>
                <!-- Add Product Button -->
                <button id="add-product-btn" class="btn btn-primary">Xác nhận thêm sản phẩm</button>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
    <link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">

    <!-- MDBootstrap JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.js"></script>


    <script>
        function removeProductVariantCard(buttonElement) {
            var productCard = $(buttonElement).closest('.product-variant-card');

            productCard.fadeOut(500, function() {
                productCard.remove();
            });
        }


        function removeSizeRow(button) {
            // Xóa phần tử fieldset chứa size, quantity, price
            $(button).closest('.custom-fieldset').remove();
        }

        function addSize (button) {
            // Lấy nội dung của template sizeQuantityPriceTemplate
            var sizeTemplate = $($('#sizeQuantityPriceTemplate').html()); // Tạo đối tượng jQuery từ template HTML

            // Thêm hiệu ứng fade-in vào template
            sizeTemplate.addClass("fade-in");

            // Thêm vào container chứa các size
            $(button).siblings('.size-container').first().append(sizeTemplate);

            // Thêm hiệu ứng hiển thị chậm
            setTimeout(() => {
                sizeTemplate.addClass("show");
            }, 100); // 100ms cho hiệu ứng chậm
        }

        var quill
        $(document).ready(function() {
            quill = new Quill('#productDescription', {
                theme: 'snow'
            });

            $('#imageInputSizeTable').on('change', function(event) {
                // Kiểm tra xem có file được chọn không
                var file = event.target.files[0];
                if (file) {
                    // Tạo URL đối tượng từ file ảnh
                    var reader = new FileReader();
                    reader.onload = function(e) {
                        // Gán URL vào thuộc tính src của thẻ img
                        $('#previewSizeTable').attr('src', e.target.result);
                    };
                    reader.readAsDataURL(file);
                }
            });

            updateProductCards()
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
                const color = $(this).find('.variant-color').val();
                let hasSizeRow = false;

                if (!color) {
                    $(this).find('.variant-color').next('.error-message').text('Vui lòng nhập màu.');
                    isValid = false;
                } else {
                    $(this).find('.variant-color').next('.error-message').text('');
                }

                $(this).find('.single-size-row').each(function() {
                    hasSizeRow = true;
                    const price = $(this).find('.variant-price').val();
                    const quantity = $(this).find('.variant-quantity').val();
                    const size = $(this).find('.variant-size').val();

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

                if (!hasSizeRow) {
                    $(this).find('.size-container').after('<div class="error-message text-danger">Vui lòng thêm ít nhất một phân loại kích cỡ.</div>');
                    isValid = false;
                } else {
                    $(this).find('.size-container').next('.error-message').remove(); // Xóa thông báo lỗi nếu đã có size
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

            const sizeTableImage = $("#imageInputSizeTable")[0];
            if (sizeTableImage) {
                formData.append(`product.sizeConversionTable`, sizeTableImage.files[0]);
            }


            let index = 0;

            $('#productVariantsContainer .product-variant-card').each(function() {
                const color = $(this).find('.variant-color').val();
                const fileInput = $(this).find(".image-upload input[type='file']")[0];

                $(this).find('.single-size-row').each(function() {
                    const variant = {
                        price: parseFloat($(this).find('.variant-price').val()),
                        size: $(this).find('.variant-size').val(),
                        quantity: parseInt($(this).find('.variant-quantity').val()),
                    };

                    formData.append(`productVariants[` + index + `].price`, variant.price);
                    formData.append(`productVariants[` + index + `].color`, color);
                    formData.append(`productVariants[` + index + `].size`, variant.size);
                    formData.append(`productVariants[` + index + `].quantity`, variant.quantity);

                    if (fileInput && fileInput.files[0]) {
                        formData.append(`productVariants[` + index + `].image`, fileInput.files[0]);
                    }

                    index += 1;
                });
            });


            // Gửi dữ liệu lên server
            $.ajax({
                url: '/api-add-product',
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
