<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="page-wrapper" style="min-height: 754px;">
    <div class="content">
        <div class="row">
            <select onchange="filter(this)" name="filter" id="filter" class="form-control select">
                <option value="">Choose a filter</option>
                <optgroup label="Year">
                    <option value="year-from:2019_to:2020">From 2019 to 2020</option>
                    <option value="year-from:2020_to:2021">From 2020 to 2021</option>
                    <option value="year-from:2021_to:2022">From 2021 to 2022</option>
                </optgroup>
                <optgroup label="Month (Present Year)">
                    <option value="month-from:1_to:6">From 1 to 6</option>
                    <option value="month-from:6_to:12">From 6 to 12</option>
                </optgroup>
            </select>
        </div>
        <div class="row text-center" style="margin-top: 20px">
            <div class="col-sm-12 col-md-12">
                <h3>Top bác sĩ theo số toa thuốc</h3>
                <canvas id="bd3"></canvas>
            </div>
        </div>
    </div>
</div>

<script>
    drawPieChart();

    function filter(obj) {
        drawPieChart(obj.value);
    }

    function drawPieChart() {
        //Pie Chart doctors
        let dt = [];
        if (arguments[0] != null) {
            fetch('/api/getTotalPrescriptionOfDoctor?filter='+arguments[0]).then(res => res.json()).then(d => dt = d)
        }
        else {
            fetch('/api/getTotalPrescriptionOfDoctor?').then(res => res.json()).then(d => dt = d)
        }

        fetch('/api/getTotalDoctors').then(res => res.json()).then(data => {
            console.log(data);
            let keys = Object.keys(data);
            let values = Object.values(data);
            let labels = [];
            let backgroundColors = [];
            for (let i = 0; i < values.length; i++) {
                labels.push(values[i].ten);
                backgroundColors.push(getRandomColor());
            }
            let lineChartData = {
                labels: labels,
                datasets: [{
                    backgroundColor: backgroundColors,
                    data: dt
                }]
            };
            let line = document.getElementById("bd3").getContext("2d");
            new Chart(line, {
                type: 'pie',
                data: lineChartData,
                options: {
                    responsive: true,
                    tooltips: {
                        mode: 'index',
                        intersect: true,
                    }
                }
            })
        })
    }


    //Line chart doctors
    fetch('/api/getTotalPatient').then(res => res.json()).then(data => {
        let lineChartData = {
            labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
            datasets: [{
                label: "Total patient in this year",
                backgroundColor: getRandomColor(),
                fill: true,
                data: data
            }]
        };
        let line = document.getElementById("bd1").getContext("2d");
        new Chart(line, {
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
        })
    })

    //Radar chart doctors
    fetch('/api/getTotalPatient').then(res => res.json()).then(data => {
        let lineChartData = {
            labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
            datasets: [{
                backgroundColor: getRandomColor(),
                fill: true,
                data: data
            }]
        };
        let line = document.getElementById("bd4").getContext("2d");
        new Chart(line, {
            type: 'radar',
            data: lineChartData,
            options: {
                plugins: {
                    filler: {
                        propagate: false
                    },
                    'samples-filler-analyser': {
                        target: 'chart-analyser'
                    }
                },
                interaction: {
                    intersect: false
                },
                responsive: true,
                legend: {
                    display: false,
                },
                tooltips: {
                    mode: 'index',
                    intersect: false,
                }
            }
        })
    })
</script>