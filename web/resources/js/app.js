var claves;
var valores;
var chartData = [];

addChart= function(keys, values, tipo, total) {
    claves= "";
    valores= "";
    chartData= [];
    
    claves= keys.split(";");
    valores= values.split(";");
    
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
