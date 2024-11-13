<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
  .table-container {
    margin: 20px auto;
    max-width: 80%;
  }
  th, td {
    text-align: center;
  }
</style>

<section>
  <div class="table-container">
    <form id="return-form" action="/tra-san-pham" method="POST">
      <table class="table table-bordered table-hover">
        <thead class="thead-dark">
        <tr>
          <th><input type="checkbox" id="select-all"></th>
          <th>ID</th>
          <th>Quantity</th>
          <th>Product Discount ID</th>
          <th>Product Variant ID</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="orderitem" items="${orderitemList}">
          <tr>
            <td>
              <input type="checkbox" class="item-checkbox" name="orderitems" value="${orderitem.id}">
              <!-- Hidden fields for quantity, productDiscount, and productVariant -->
              <input type="hidden" name="quantity-${orderitem.id}" value="${orderitem.quantity}">
              <input type="hidden" name="productDiscount-${orderitem.id}" value="${orderitem.productDiscount.id}">
              <input type="hidden" name="productVariant-${orderitem.id}" value="${orderitem.productVariant.id}">
            </td>
            <td>${orderitem.id}</td>
            <td>${orderitem.quantity}</td>
            <td>${orderitem.productDiscount.id}</td>
            <td>${orderitem.productVariant.id}</td>
          </tr>
        </c:forEach>
        </tbody>
      </table>

      <div style="text-align: center;">
        <button type="submit" class="primary-btn">Trả sản phẩm</button>
      </div>
    </form>
  </div>
</section>


<script>
  document.getElementById('select-all').addEventListener('change', function() {
    const checkboxes = document.querySelectorAll('.item-checkbox');
    const isChecked = this.checked;
    checkboxes.forEach(checkbox => {
      checkbox.checked = isChecked;
    });
  });
</script>
