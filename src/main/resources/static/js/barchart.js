google.charts.load('current', {packages: ['corechart']});
google.charts.setOnLoadCallback(drawChart);

// 차트 그리기 함수
async function drawChart() {
	try {
		const queryString = window.location.search;
		const urlParams = new URLSearchParams(queryString);
		const timetableId = urlParams.get('id');
		
		const response = await fetch(`/api/grade-data?id=${timetableId}`);
		const barData = await response.json();
		
		const dataArray = [['Lecture', 'Grade']];
		barData.forEach(item => {
				            dataArray.push([
				                item.lecture,
				                item.grade
				            ]);
				        });
		const data = google.visualization.arrayToDataTable(dataArray);
		
		const barOptions = {
			chartArea: { left: 70, top: 50, width: '90%', height: '70%' },
			legend: { position: 'none' },
			pointSize: 10,
			vAxis: {viewWindow: {min: 0.0, max: 4.5}, gridlines: { count: 9 }},
			bar: { groupWidth: '50%' }, // 막대 너비
		};
			
		const barChart = new google.visualization.ColumnChart(document.getElementById('bar_chart'));
		barChart.draw(data, barOptions);
	} catch (error) {
		console.error('Error fetching or drawing chart data:', error);
	}
}