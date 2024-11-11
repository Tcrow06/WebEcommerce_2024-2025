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
    <table class="table table-bordered table-hover">
      <thead class="thead-dark">
      <tr>
        <th><input type="checkbox" id="select-all"></th>
        <th>ID</th>
        <th>Quantity</th>
        <th>Order ID</th>
        <th>Product Discount ID</th>
        <th>Product Variant ID</th>
      </tr>
      </thead>
      <tbody>
      <!-- Example rows, replace with dynamic rows if fetching data from backend -->
      <c:forEach var="item-return" items="${model}">
        <tr>
          <td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"></td>
          <td>${item.quantity}</td>
          <td>${item.orderId}</td>
          <td>${item.orderId}</td>
          <td>${item.orderId}</td>
        </tr>
      </c:forEach>
      <!-- Add more rows as needed -->
      </tbody>
    </table>
  </div>
</section>

<script>
  // Select/Deselect all checkboxes based on the "select-all" checkbox
  document.getElementById('select-all').addEventListener('change', function () {
    const checkboxes = document.querySelectorAll('.row-checkbox');
    checkboxes.forEach(checkbox => checkbox.checked = this.checked);
  });

  // Optionally, keep the "select-all" checkbox in sync when row checkboxes are changed
  const rowCheckboxes = document.querySelectorAll('.row-checkbox');
  rowCheckboxes.forEach(checkbox => {
    checkbox.addEventListener('change', function () {
      document.getElementById('select-all').checked =
              Array.from(rowCheckboxes).every(cb => cb.checked);
    });
  });
</script>