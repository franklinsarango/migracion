
    
var dialogBox;

// Dialog
function showMapInDialog(utmNorte, utmEste, Dialog) {
    //alert('Hola ' + utmNorte + ' ' + utmEste);
    
    dialogBox = new digit.Dialog({
            title: "My Map"
        });
        dialogBox.show();
}
