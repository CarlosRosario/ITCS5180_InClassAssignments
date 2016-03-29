// Initialize app
var myApp = new Framework7();
 
var $$ = Dom7;
 
var mainView = myApp.addView('.view-main', {
  dynamicNavbar: true
});
 
/*=== Default standalone ===*/
var myPhotoBrowserStandalone = myApp.photoBrowser({
    photos : [
        'http://lorempixel.com/1024/1024/sports/1/',
        'http://lorempixel.com/1024/1024/sports/2/',
        'http://lorempixel.com/1024/1024/sports/3/',
    ]
});
//Open photo browser on click
$$('.pb-standalone').on('click', function () {
    myPhotoBrowserStandalone.open();
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
 
/*=== As Page ===*/
var myPhotoBrowserPage = myApp.photoBrowser({
    photos : [
        'http://lorempixel.com/1024/1024/sports/1/',
        'http://lorempixel.com/1024/1024/sports/2/',
        'http://lorempixel.com/1024/1024/sports/3/',
    ],
    type: 'page',
    backLinkText: 'Back'
});
$$('.pb-page').on('click', function () {
    myPhotoBrowserPage.open();
});
 
/*=== Standalone Dark ===*/
var myPhotoBrowserDark = myApp.photoBrowser({
    photos : [
        'http://lorempixel.com/1024/1024/sports/1/',
        'http://lorempixel.com/1024/1024/sports/2/',
        'http://lorempixel.com/1024/1024/sports/3/',
    ],
    theme: 'dark'
});
$$('.pb-standalone-dark').on('click', function () {
    myPhotoBrowserDark.open();
});
 
/*=== Popup Dark ===*/
var myPhotoBrowserPopupDark = myApp.photoBrowser({
    photos : [
        'http://lorempixel.com/1024/1024/sports/1/',
        'http://lorempixel.com/1024/1024/sports/2/',
        'http://lorempixel.com/1024/1024/sports/3/',
    ],
    theme: 'dark',
    type: 'popup'
});
$$('.pb-popup-dark').on('click', function () {
    myPhotoBrowserPopupDark.open();
}); 
 
/*=== With Captions ===*/
var myPhotoBrowserPopupDark = myApp.photoBrowser({
    photos : [
        {
            url: 'http://lorempixel.com/1024/1024/sports/1/',
            caption: 'Caption 1 Text'
        },
        {
            url: 'http://lorempixel.com/1024/1024/sports/2/',
            caption: 'Second Caption Text'
        },
        // This one without caption
        {
            url: 'http://lorempixel.com/1024/1024/sports/3/',
        },
    ],
    theme: 'dark',
    type: 'standalone'
});
$$('.pb-standalone-captions').on('click', function () {
    myPhotoBrowserPopupDark.open();
});
 
/*=== With Video ===*/
var myPhotoBrowserPopupDark = myApp.photoBrowser({
    photos : [
        {
            html: '<iframe src="//www.youtube.com/embed/lmc21V-zBq0?list=PLpj0FBQgLGEr3mtZ5BTwtmSwF1dkPrPRM" frameborder="0" allowfullscreen></iframe>',
            caption: 'Woodkid - Run Boy Run (Official HD Video)'
        },
        {
            url: 'http://lorempixel.com/1024/1024/sports/2/',
            caption: 'Second Caption Text'
        },
        {
            url: 'http://lorempixel.com/1024/1024/sports/3/',
        }
    ],
    theme: 'dark',
    type: 'standalone'
});
$$('.pb-standalone-video').on('click', function () {
    myPhotoBrowserPopupDark.open();
});