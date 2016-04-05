// Initialize app
var myApp = new Framework7();
 
var $$ = Dom7;
 
var mainView = myApp.addView('.view-main', {
  dynamicNavbar: true
});
 
 
/*=== View Gallery ===*/
var myPhotoBrowserPopup = myApp.photoBrowser({
    photos : [
        'https://farm9.staticflickr.com/8645/16031539611_b45d2bbe57_z.jpg',
        'https://farm4.staticflickr.com/3933/15296234239_4f11d889a8_z.jpg',
        'https://farm3.staticflickr.com/2949/15296430490_3366e6f050_z.jpg',
        'https://farm4.staticflickr.com/3937/15483139575_c6eced4510_z.jpg'
        
    ],
    theme: 'dark',
    type: 'popup'
});
$$('.pb-ViewGallery').on('click', function () {
    myPhotoBrowserPopup.open();
});

$$('.pb-TakePicture').on('click', function() {
    location.href = "./camera.html"
});
