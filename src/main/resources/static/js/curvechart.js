google.charts.load('current', {packages: ['corechart']});
google.charts.setOnLoadCallback(fetchAndDrawChart);

// 차트 그리기 함수
async function fetchAndDrawChart() {
    try {
        const response = await fetch('/api/average-data');
        const chartData = await response.json();

        const dataArray = [['Term', 'Grade', { role: 'style' }]];
		chartData.forEach(item => {
			if(item.average != 0) {
	            dataArray.push([
	                item.term,
	                item.average,
	                'point {fill-color: white; stroke-color: red; stroke-width: 2}'
	            ]);				
			}
		});

        const data = google.visualization.arrayToDataTable(dataArray);

        const options = {
            chartArea: { left: 70, top: 50, width: '85%', height: '70%' },
            legend: { position: 'none' },
            series: { 0: { color: 'red' } },
            pointSize: 7,
            vAxis: {
                viewWindow: { min: 2.0, max: 4.5 },
                gridlines: { count: 5 }
            },
        };

        const chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
        chart.draw(data, options);
    } catch (error) {
        console.error('Error fetching or drawing chart data:', error);
    }
}