'use strict';

window.onload = function () {
  var navLinks = document.getElementsByClassName('page-link');

  for (var i = 0; i < navLinks.length; i++) {
    if (navLinks[i].href === window.location.href) {
      document.getElementById(navLinks[i].innerHTML).className += " current";
    }
  }
}
