function getRandomColor() {
	let letters = '0123456789ABCDEF';
	let color = '#';
	for (let i = 0; i < 6; i++) {
		color += letters[Math.floor(Math.random() * 16)];
	}
	return color;
}
$(document).ready(function () {
    // Bar Chart
    fetch("/api/getTypeOfDisease").then(res => res.json()).then(data => {
        let ids = Object.keys(data);
        let names = Object.values(data);
        let years = ['2018', '2019', '2020', '2021'];
        let datasets = [];

        for (let i = 0; i < ids.length; i++) {
            let datas = [];
            for (let j = 2018; j < 2022; j++) {
                fetch("/api/getTotalPatientOnDisease?id=" + ids[i] + "&year=" + j).then(res => res.json())
                    .then(dt => {
                        datas.push(dt);
                    })
            }
            // console.log("datas: ", datas);
            // console.log("is array: ", Array.isArray(datas));
            let a1 = datas[0];
            let a2 = datas[1];
            let a3 = datas[2];
            let a4 = datas[3];
            // console.log("a: ", a1, a2, a3, a4);
            let obj = {
                label: names[i],
                data: [a1, a2, a3, a4]
            }
            datasets.push(obj);

        }
        // console.log(datasets);
        let barChartData = {
            labels: years,
            datasets: datasets
        };

        let ctx = document.getElementById('bargraph').getContext('2d');
        window.myBar = new Chart(ctx, {
            type: 'bar',
            data: barChartData,
            options: {
                responsive: true,
                legend: {
                    display: false,
                }
            }
        });
    })

    // Line Chart
    fetch('/api/getTotalPatient').then(res => res.json()).then(data => {
        var lineChartData = {
            labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
            datasets: [{
                label: "Total patient in this year",
                backgroundColor: "rgba(0, 158, 251, 0.5)",
                fill: true,
                data: data
            }]
        };

        var linectx = document.getElementById('linegraph').getContext('2d');
        window.myLine = new Chart(linectx, {
            type: 'line',
            data: lineChartData,
            options: {
                responsive: true,
                legend: {
                    display: false,
                },
                tooltips: {
                    mode: 'index',
                    intersect: false,
                }
            }
        });
    })

    // Bar Chart 2

    barChart();

    $(window).resize(function () {
        barChart();
    });

    function barChart() {
        $('.bar-chart').find('.item-progress').each(function () {
            var itemProgress = $(this),
                itemProgressWidth = $(this).parent().width() * ($(this).data('percent') / 100);
            itemProgress.css('width', itemProgressWidth);
        });
    };
});