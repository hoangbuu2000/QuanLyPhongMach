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
                    <option value="2019">2019</option>
                    <option value="2020">2020</option>
                    <option value="2021">2021</option>
                </optgroup>
            </select>
        </div>
        <div class="row text-center" style="margin-top: 20px">
            <div class="col-sm-12 col-md-12">
                <h3>Tổng bệnh nhân trong năm</h3>
                <canvas id="bd1"></canvas>
            </div>
        </div>
    </div>
</div>

<script>

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