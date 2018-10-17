function initialize() {
		
	var y = 37.5656191; // Y 좌표
	var x = 127.01188309999998; // X 좌표
	var zoomLevel = 16; // 첫 로딩시 보일 지도의 확대 레벨
	var markerTitle = "토다이 중계점"; // 현재 위치 마커에 마우스를 올렸을때 나타나는 이름
	var markerMaxWidth = 300; // 마커를 클릭했을때 나타나는 말풍선의 최대 크기
	
	// 말풍선 내용
	var contentString = '<div>' +
							'<h2 style="margin: 8px 0px;">한양공업고등학교</h2>' +
							'<p>우리 학교 ㅎㅎ</p>'
						'</div>';
	
	var myLatlng = new google.maps.LatLng(y, x);
	var mapOptions = {
		zoom: zoomLevel,
		center: myLatlng,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	}
	 
	var map = new google.maps.Map(document.getElementById('map_view'), mapOptions);
	
	var marker = new google.maps.Marker({
		position: myLatlng,
		map: map,
		title: markerTitle
	});
	
	var infowindow = new google.maps.InfoWindow({
		content: contentString,
		maxWidth: markerMaxWidth
	});
	
	infowindow.open(map, marker);
	
	google.maps.event.addListener(marker, 'click', function() {
		infowindow.open(map, marker);
	});
}