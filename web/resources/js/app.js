var datosGrafico;
var claves;
var valores;
var chartData = [];

addChart= function(data, tipo, total) {
    datosGrafico= "";
    claves= "";
    valores= "";
    chartData= [];
    
    datosGrafico= data.split("-|-");
    claves= datosGrafico[0].split(";");
    valores= datosGrafico[1].split(";");
    
    var data = [];
    for(i=0; i<claves.length; i++) {
        data= [claves[i] , valores[i]];
        chartData.push(data);
    }
    
    chart = c3.generate({
        bindto: "#chart",
        data: {
            type: tipo,
            columns: chartData
        },
        tooltip: {
            format: {
                title: function(x) {
                    return 'TOTAL: ' + total;
                }
            }
        },
    })
}
