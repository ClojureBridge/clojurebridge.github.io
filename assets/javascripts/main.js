'use strict';

//Adds or removes a class to toggle the menu dropdown
function dropDown() {
  var header = document.getElementById("header-content");
  var headerClasses = header.className.split(/\s+/);
  var index = headerClasses.indexOf("mobile-dropdown");

  if (index >= 0) {
    headerClasses.splice(index, 1);
  } else {
    headerClasses.push("mobile-dropdown");
  }
  header.className = headerClasses.join(" ");
}

var menuButton = document.getElementById('menu-button');
menuButton.addEventListener('click', dropDown);
