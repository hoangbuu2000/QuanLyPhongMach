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
                    <option value="2018-2019">From 2018 To 2019</option>
                    <option value="2019-2020">From 2019 To 2020</option>
                    <option value="2020-2021">From 2020 To 2021</option>
                </optgroup>
            </select>
        </div>
        <div class="row text-center" style="margin-top: 20px">
            <div class="col-sm-12 col-md-12">
                <h3>Doanh thu qua các năm</h3>
                <canvas id="bd1"></canvas>
            </div>
        </div>
    </div>
</div>

<script>
    drawBar();

    function filter(obj) {
        drawBar(obj.value);
    }

    function drawBar() {
        if (arguments[0] != null && arguments[0].length > 0) {
            let array = arguments[0].split("-");
            fetch('/api/getTotalSales?from='+array[0]+'&to='+array[1]).then(res => res.json())
                .then(data => {
                    //Bar Chart sales
                    let barChartData = {
                        labels: array,
                        datasets: [{
                            label: 'Dataset 1',
                            backgroundColor: 'rgba(0, 158, 251, 0.5)',
                            borderColor: 'rgba(0, 158, 251, 1)',
                            borderWidth: 1,
                            data: data
                        }]
                    };

                    let ctx = document.getElementById('bd1').getContext('2d');
                    new Chart(ctx, {
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
        }
        else {
            let d = new Date();
            let array = [d.getFullYear() - 3, d.getFullYear() - 2, d.getFullYear() - 1, d.getFullYear()];
            fetch('/api/getTotalSales?from='+array[0]+'&to='+array[3]).then(res => res.json())
                .then(data => {
                    //Bar Chart sales
                    let barChartData = {
                        labels: array,
                        datasets: [{
                            label: 'Dataset 1',
                            backgroundColor: 'rgba(0, 158, 251, 0.5)',
                            borderColor: 'rgba(0, 158, 251, 1)',
                            borderWidth: 1,
                            data: data
                        }]
                    };

                    let ctx = document.getElementById('bd1').getContext('2d');
                    new Chart(ctx, {
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
        }

    }


</script>