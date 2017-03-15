'use strict';

function dropDown() {
  console.log("clicked");
}


var menuButton = document.getElementById('menu-button');
menuButton.addEventListener('click', dropDown);
