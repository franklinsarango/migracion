/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function toUpper(cadena) {
    return cadena.toUpperCase();
}

function acceptText(e) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla === 8 || tecla === 9 || tecla === 0)
        return true;
    patron = /[A-Za-zñÑ\s]/;
    te = String.fromCharCode(tecla);
    te = te.toUpperCase();
    return patron.test(te);
}

function acceptUpperText(e) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla === 8 || tecla === 9 || tecla === 0)
        return true;
    patron = /[A-Za-zñÑ\s]/;
    te = String.fromCharCode(tecla);
    te = te.toUpperCase();
    return te;
}

function acceptNum(e){
    tecla = (document.all) ? e.keyCode : e.which; 
    if (tecla==8 || tecla==9 || tecla==0) return true; 
    patron = /\d/;
    te = String.fromCharCode(tecla); 
    return patron.test(te); 
}


