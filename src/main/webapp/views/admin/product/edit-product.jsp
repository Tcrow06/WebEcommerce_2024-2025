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
        background-color: #FFFFFF;
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

    .header-shopping {
        background-image: url("https://images.unsplash.com/photo-1487744480471-9ca1bca6fb7d?q=80&w=2091&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" );
        background-size: 100%, 100%;
        color: rgb(241, 239, 239);
        margin-bottom: 20px;
        padding: 100px; height: 400px
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

        <header class="bg-light header-shopping">
            <div class="container text-center">
                <h1 class="display-4">Chính sửa sản phẩm</h1>
                <p class="lead">Mua sắm cùng Eleven !</p>
            </div>
        </header>

        <div class="row">
            <div class="form-group">
                <h2 class="name">
                    <input type="hidden" id="product-id" value="${model.id}">
                    <input type="hidden" id="product-status" value="${model.status}">

                    <small>Product name @
                        <div class="row">
                            <div class=" mb-4">
                                <div data-mdb-input-init class="form-outline">
                                    <input type="text" name="firstName" id="productName" class="custom-input form-control form-control-lg" value="${model.name}" />
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
                                    <input type="text" name="firstName" id="productBrand" class="custom-input form-control form-control-lg" value="${model.brand}" />
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
                    <c:forEach var="item" items="${category}">
                        <option data-id="${item.id}" value="${item.code}">${item.name}</option>
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
                        <input type="hidden" name="category" id="category" value="${model.category.id}"
                               class="custom-input form-control form-control-lg" />
                    </ul>
                </div>
            </div>
            <hr>
            <div class="description description-tabs">
                <div class="tab-content" style="height: 400px">
                    <br />
                    <strong>Description Title</strong>
                    <div class="mb-3">
                        <div id="productDescription" style="height: 320px"></div>
                    </div>
                </div>
            </div>
            <div class="size-table">
                <br />
                <strong>Up ảnh bảng size</strong>
                <div class="product-image">
                    <div class="item active">
                        <img src="<c:url value='/api-image?path=${model.sizeConversionTableUrl}'/>" class="img-responsive" alt="Product Image" id = "previewSizeTable">
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
                                <img src="<c:url value='/static/img/not-delete/404.jpg'/>" class="img-responsive" alt="Product Image">
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
                                <div class="error-message text-danger" style="margin: 10px"></div>
                            </div>
                        </div>
                        <div class="w-100"></div>
                        <div class="row size-container">
                            <!-- Các ô Size, Quantity và Price sẽ được thêm vào đây -->
                        </div>
                        <div class="error-message text-danger" style="margin: 10px"></div>
                        <button type="button" class="col align-items-center btn btn-primary btn-rounded add-size-btn mt-5" style="max-width: 200px; max-height: 40px;" onclick="addSize(this)">Thêm Size</button>
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
                            <button class="btn btn-dark" onclick="removeProductVariantCard(this)" >Hủy</button>
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
                    <span class="input-group-text">-</span>
                    <input
                            type="number"
                            class="form-control variant-quantity"
                            placeholder="Quantity"
                            aria-label="Quantity"
                    />
                    <span class="input-group-text">-</span>
                    <input
                            type="text"
                            class="form-control variant-price"
                            placeholder="Price"
                            aria-label="Price"
                    />
                    <span class="input-group-text">VND</span>
                    <div class="d-flex align-items-center justify-content-center">
                        <button type="button" class="btn btn-dark btn-sm remove-row-btn ms-2" style="font-size: 0.8rem;" onclick="removeSizeRow(this)">🗑</button>
                    </div>
                </div>
                <div class="error-message text-danger" style="margin: 10px"></div>
            </div>

        </fieldset>
    </template>





    <div id="productVariantsContainer" class="row mt-4">
        <br>
        <strong>Chi tiết sản phẩm </strong>
        <div class="error-message-variant text-danger" style="margin: 10px"></div>

        <c:forEach var="item" items="${model.getProductVariantColorsss()}">
            <div class="product-variant-card card mb-4">
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-5 col-sm-12 col-xs-12">
                            <div class="product-image">
                                <div class="item active">
                                    <img src="<c:url value='/api-image?path=${item.imageUrl}'/>" class="img-responsive product-img" alt="Product Image">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-7 col-md-offset-1 col-sm-12 col-xs-12 row">
                            <div class="col">
                                <div class="input-group input-group-sm mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text btn-primary" style="color: white;" >Color</span>
                                    </div>
                                    <input type="text" class="form-control variant-color" name="name" aria-label="Small" aria-describedby="inputGroup-sizing-sm" value="${item.color}" style="max-width: 150px;">
                                    <div class="error-message"></div>
                                </div>
                            </div>
                            <div class="w-100"></div>
                            <div class="row size-container">
                                <c:forEach var="sizeProduct" items="${item.sizes}">
                                    <fieldset class="custom-fieldset border-bottom" style="max-height: 80px; margin: 10px">
                                        <legend class="custom-legend">Phân loại hàng</legend>
                                        <div class="d-flex align-items-center mb-2 single-size-row">

                                            <input type="hidden" class="variant-id" value="${sizeProduct.id}">


                                            <div class="input-group mb-3">
                                                <input
                                                        type="text"
                                                        class="form-control variant-size"
                                                        placeholder="Size"
                                                        aria-label="Size"
                                                        value="${sizeProduct.size}"
                                                />
                                                <span class="input-group-text">-</span>
                                                <input
                                                        type="number"
                                                        class="form-control variant-quantity"
                                                        placeholder="Quantity"
                                                        aria-label="Quantity"
                                                        value="${sizeProduct.quantity}"
                                                />
                                                <span class="input-group-text">-</span>
                                                <input
                                                        type="text"
                                                        class="form-control variant-price"
                                                        placeholder="Price"
                                                        aria-label="Price"
                                                        value="${sizeProduct.price}"
                                                />
                                                <span class="input-group-text">VND</span>
                                                <div class="d-flex align-items-center justify-content-center">
                                                    <button type="button" class="btn btn-dark btn-sm remove-row-btn ms-2" style="font-size: 0.8rem;" onclick="removeSizeRow(this)">🗑</button>
                                                </div>
                                            </div>
                                            <div class="error-message text-danger" style="margin: 10px"></div>
                                        </div>
                                    </fieldset>
                                </c:forEach>
                            </div>
                            <button type="button" class="col align-items-center btn btn-primary btn-rounded add-size-btn mt-5" style="max-width: 200px; max-height: 40px;" onclick="addSize(this)">Thêm Size</button>
                            <div class="w-100"></div>
                            <div class="col" style="margin-top: 20px;">
                                <div class="form-group">
                                    <label> Product Image</label>
                                    <div class="image-upload">
                                        <input type="file" accept="image/jpeg" onchange="previewImage(this)">
                                        <div class="image-uploads">
                                            <img src="/static/admin/assets/img/icons/upload.svg" alt="img">
                                            <h4>Drag and drop a file to upload</h4>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="mt-3 d-flex justify-content-end">
                                <button class="btn btn-dark" onclick="removeProductVariantCard(this)" >Hủy</button>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

        </c:forEach>


    </div>

    <div id="update-div" class="product-content product-wrap clearfix product-deatil">
        <div class="row">
            <!-- Confirm and Cancel Buttons -->
            <div class="col d-flex justify-content-between align-items-center">
                <button id="stopSellingBtn" class="btn btn-dark" onclick="stopSellingProduct()">Ngừng kinh doanh sản phẩm này</button>
                <!-- Cancel Button -->
                <div class="d-flex">
                    <button id="updateVariantBtn" class="btn btn-secondary me-2" onclick="updateProductCards()">+ Thêm phân loại sản phẩm</button>
                    <!-- Add Product Button -->
                    <button id="add-product-btn" class="btn btn-primary">Xác nhận chỉnh sửa</button>
                </div>
            </div>
        </div>
    </div>


    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Xác nhận</h5>
                    <button type="button" class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Bạn có chắc chắn muốn thực hiện ?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-mdb-dismiss="modal" id="cancelButton">Hủy</button>
                    <button type="button" class="btn btn-primary" id="okButton">OK</button>
                </div>
            </div>
        </div>
    </div>


    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
    <link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">

    <!-- MDBootstrap JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.js"></script>


    <script>


        if ($('#product-status').val() == 'STOP_SELLING') {
            $('#update-div').hide()
        }

        function removeProductVariantCard(buttonElement) {
            var productCard = $(buttonElement).closest('.product-variant-card');

            productCard.fadeOut(500, function() {
                productCard.remove();
            });
        }


        function previewImage(input) {
            // Tìm thẻ img nằm trong cùng thẻ cha của input file
            var parentCard = $(input).closest('.product-variant-card');  // Lấy thẻ cha gần nhất có class 'product-variant-card'
            var img = parentCard.find('img.img-responsive');  // Tìm thẻ <img> trong thẻ cha đó

            // Kiểm tra xem có tệp được chọn không
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function(e) {
                    // Thay đổi src của thẻ img bằng URL tạm thời của hình ảnh đã chọn
                    img.attr('src', e.target.result);
                };
                // Đọc tệp hình ảnh dưới dạng URL
                reader.readAsDataURL(input.files[0]);
            }
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

        function updateCategory () {
            // Giá trị `id` từ input ẩn
            const selectedCategoryId = $('#category').val();

            // Tìm và đặt tùy chọn phù hợp
            $('#categorySelect option').each(function () {
                if ($(this).data('id') == selectedCategoryId) {
                    $(this).prop('selected', true);
                    return false; // Thoát vòng lặp
                }
            });

            // Cập nhật tên và mã danh mục
            const selectedOption = $('#categorySelect option:selected');
            $('#categoryName').text(selectedOption.text());
            $('#categoryCode').text(selectedOption.val());

            // Lắng nghe sự kiện thay đổi và cập nhật thông tin
            $('#categorySelect').change(function () {
                const selectedOption = $(this).find(':selected');
                $('#categoryName').text(selectedOption.text());
                $('#categoryCode').text(selectedOption.val());
            });
        }

        var quill
        $(document).ready(function() {
            updateCategory()

            quill = new Quill('#productDescription', {
                theme: 'snow'
            });

            quill.root.innerHTML = '${model.description}';

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
            $('#add-product-btn').click(updateProduct);
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

            let isProductVariant = false;

            $('#productVariantsContainer .product-variant-card').each(function(index) {
                const color = $(this).find('.variant-color').val();
                let hasSizeRow = false;
                isProductVariant = true;

                if (!color) {
                    $(this).find('.variant-color').next('.error-message').text('Vui lòng nhập màu.');
                    isValid = false;
                } else {
                    $(this).find('.variant-color').next('.error-message').text('');
                }


                $(this).find('.single-size-row').each(function() {
                    let isValidVariant = true;
                    hasSizeRow = true;

                    const price = $(this).find('.variant-price').val();
                    const quantity = $(this).find('.variant-quantity').val();
                    const size = $(this).find('.variant-size').val();

                    if (!size) {
                        isValidVariant = false;
                        isValid = false;
                    }

                    if (!quantity || isNaN(quantity) || parseInt(quantity) <= 0) {
                        isValidVariant = false;
                        isValid = false;
                    }

                    if (!price || isNaN(price) || parseFloat(price) <= 0) {
                        isValidVariant = false;
                        isValid = false;
                    }

                    if (!isValidVariant) {
                        $(this).find('.error-message').text("Phân loại hàng không hợp lệ !");
                    } else {
                        $(this).find('.error-message').text(""); // Clear lỗi nếu hợp lệ
                    }
                });

                if (!hasSizeRow) {
                    $(this).find('.size-container').after('<div class="error-message text-danger">Vui lòng thêm ít nhất một phân loại kích cỡ.</div>');
                    isValid = false;
                } else {
                    $(this).find('.size-container').next('.error-message').remove(); // Xóa thông báo lỗi nếu đã có size
                }
                
            });

            if (!isProductVariant) {
                $('#productVariantsContainer').find('.error-message-variant').text("Vui lòng thêm ít nhất một phân loại sản phẩm")
                isValid = false;
            }
            else $('#productVariantsContainer').find('.error-message-variant').text("")

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

        function showConfirmationModal() {
            return new Promise((resolve) => {
                // Hiển thị modal
                $('#exampleModal').modal('show');

                // Khi người dùng nhấn "OK"
                $('#okButton').one('click', function () {
                    resolve(true); // Người dùng đồng ý
                    $('#exampleModal').modal('hide');
                });

                // Khi người dùng nhấn "Cancel"
                $('#cancelButton').one('click', function () {
                    resolve(false); // Người dùng hủy
                    $('#exampleModal').modal('hide');
                });
            });
        }


        function stopSellingProduct() {

            // Hiển thị modal và chờ phản hồi từ người dùng
            showConfirmationModal().then((result) => {
                if (!result) {
                    console.log("User cancelled the action.");
                    return; // Người dùng chọn "Cancel", dừng xử lý
                }

                const formData = new FormData();

                formData.append('product.id', $('#product-id').val());

                sendData(formData, 'DELETE')
            });
        }


        function updateProduct() {
            if (!checkInput()) return;


            // Hiển thị modal và chờ phản hồi từ người dùng
            showConfirmationModal().then((result) => {
                if (!result) {
                    console.log("User cancelled the action.");
                    return; // Người dùng chọn "Cancel", dừng xử lý
                }


                const formData = new FormData();

                var product = {
                    id: $('#product-id').val(),
                    name: $('#productName').val(),
                    highlight: $('#highlight').is(':checked'),
                    status: 'SELLING',
                    brand: $('#productBrand').val(),
                    description: quill.root.innerHTML,
                    category: {
                        id: $('#category').val(),
                    },
                };

                formData.append('product.id', product.id);
                formData.append('product.name', product.name);
                formData.append('product.highlight', product.highlight);
                formData.append('product.status', product.status);
                formData.append('product.brand', product.brand);
                formData.append('product.description', product.description);
                formData.append('product.category.id', product.category.id);

                const sizeTableImage = $("#imageInputSizeTable")[0];
                if (sizeTableImage && sizeTableImage.files[0]) {
                    formData.append(`product.sizeConversionTable`, sizeTableImage.files[0]);
                }


                let index = 0;

                $('#productVariantsContainer .product-variant-card').each(function () {
                    const color = $(this).find('.variant-color').val();
                    const fileInput = $(this).find(".image-upload input[type='file']")[0];

                    $(this).find('.single-size-row').each(function () {
                        const variant = {
                            id: $(this).find('.variant-id').val(),
                            price: parseFloat($(this).find('.variant-price').val()),
                            size: $(this).find('.variant-size').val(),
                            quantity: parseInt($(this).find('.variant-quantity').val()),
                        };

                        formData.append(`productVariants[` + index + `].index`, index);
                        formData.append(`productVariants[` + index + `].id`, variant.id);
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

                sendData(formData, 'POST')
            });
        }

        function sendData (data,method) {
            $.ajax({
                url: '/api-update-product',
                type: method,
                data: data,
                processData: false,  // Không xử lý dữ liệu
                contentType: false,  // Để trình duyệt tự xử lý content-type
                beforeSend: function () {
                    // Hiển thị loader trước khi AJAX bắt đầu
                    $('#global-loader').css('display', 'flex');
                },
                success: function(response) {
                    Swal.fire({
                        icon: 'success',
                        title: 'Thành công',
                        text: response.toString()
                    }).then(() => {
                        window.location.href = 'danh-sach-san-pham'
                    });
                },
                error: function(xhr, status, error) {
                    const errorMessage = xhr.responseJSON ? xhr.responseJSON.message : error;
                    Swal.fire({
                        icon: 'error',
                        title: 'Lỗi hệ thống',
                        text: "Không thể chỉnh sửa sản phẩm: " + errorMessage
                    });
                },
                complete: function () {
                    $('#global-loader').css('display', 'none');
                }
            });
        }

    </script>

</div>
