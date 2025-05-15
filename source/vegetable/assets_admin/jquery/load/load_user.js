$(document).ready(function () {
  var username = localStorage.getItem("username");

  if (username !== null) {
    var userItem2 = $(
      '<li class="sidemenu-wrap d-none d-lg-flex">' +
        '<a href="#">' +
        username +
        '<i class="fa fa-caret-down"></i> </a>' +
        '<ul class="dropdown-sidemenu dropdown-hover-2 dropdown-language">' +
        '<li><a class="btn-logout">Logout</a></li>' +
        "</ul>" +
        "</li>"
        +
        `<li class="minicart-wrap" style="margin-right:10px;">
        <a href="#" class="minicart-btn toolbar-btn">
          <i class="fa-solid fa-bell" style="color: #FFD43B;"></i>
          <span class="cart-item_count">0</span>
        </a>
        <div class="dropdown-hover-2 notify"></div>
        </li>`
    );
        
    $(".add-name").replaceWith(userItem2);
  }
  handleLogotButtonClick();
});
function handleLogotButtonClick() {
  $("body").on("click", ".btn-logout", function () {
    clearLocalStorage();
    window.location.href = "index_logined.html";
  });
}
function clearLocalStorage() {
  localStorage.removeItem("username");
  localStorage.removeItem("Token");
}
