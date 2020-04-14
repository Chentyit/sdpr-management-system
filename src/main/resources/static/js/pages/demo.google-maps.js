!function (e) {
    "use strict";
    var t = function () {
    };
    t.prototype.createBasic = function (e) {
        return new GMaps({div: e, lat: -12.043333, lng: -77.028333})
    }, t.prototype.createMarkers = function (e) {
        var t = new GMaps({div: e, lat: -12.043333, lng: -77.028333});
        return t.addMarker({
            lat: -12.043333,
            lng: -77.03,
            title: "Lima",
            details: {database_id: 42, author: "HPNeo"},
            click: function (e) {
                console.log && console.log(e), alert("You clicked in this marker")
            }
        }), t.addMarker({
            lat: -12.042,
            lng: -77.028333,
            title: "Marker with InfoWindow",
            infoWindow: {content: "<p>HTML Content</p>"}
        }), t
    }, t.prototype.createWithStreetview = function (e, t, l) {
        return GMaps.createPanorama({el: e, lat: t, lng: l})
    }, t.prototype.createMapByType = function (e, t, l) {
        var r = new GMaps({
            div: e,
            lat: t,
            lng: l,
            mapTypeControlOptions: {mapTypeIds: ["hybrid", "roadmap", "satellite", "terrain", "osm", "cloudmade"]}
        });
        return r.addMapType("osm", {
            getTileUrl: function (e, t) {
                return "http://tile.openstreetmap.org/" + t + "/" + e.x + "/" + e.y + ".png"
            }, tileSize: new google.maps.Size(256, 256), name: "OpenStreetMap", maxZoom: 18
        }), r.addMapType("cloudmade", {
            getTileUrl: function (e, t) {
                return "http://b.tile.cloudmade.com/8ee2a50541944fb9bcedded5165f09d9/1/256/" + t + "/" + e.x + "/" + e.y + ".png"
            }, tileSize: new google.maps.Size(256, 256), name: "CloudMade", maxZoom: 18
        }), r.setMapTypeId("osm"), r
    }, t.prototype.createWithStyle = function (e, t) {
        new GMaps({div: e, lat: -12.043333, lng: -77.028333, styles: t})
    }, t.prototype.init = function () {
        var t = this;
        e(document).ready(function () {
            t.createBasic("#gmaps-basic"), t.createMarkers("#gmaps-markers"), t.createWithStreetview("#panorama", 40.7295174, -73.9986496), t.createMapByType("#gmaps-types", -12.043333, -77.028333), t.createWithStyle("#ultra-light", [{
                featureType: "water",
                elementType: "geometry",
                stylers: [{color: "#e9e9e9"}, {lightness: 17}]
            }, {
                featureType: "landscape",
                elementType: "geometry",
                stylers: [{color: "#f5f5f5"}, {lightness: 20}]
            }, {
                featureType: "road.highway",
                elementType: "geometry.fill",
                stylers: [{color: "#ffffff"}, {lightness: 17}]
            }, {
                featureType: "road.highway",
                elementType: "geometry.stroke",
                stylers: [{color: "#ffffff"}, {lightness: 29}, {weight: .2}]
            }, {
                featureType: "road.arterial",
                elementType: "geometry",
                stylers: [{color: "#ffffff"}, {lightness: 18}]
            }, {
                featureType: "road.local",
                elementType: "geometry",
                stylers: [{color: "#ffffff"}, {lightness: 16}]
            }, {
                featureType: "poi",
                elementType: "geometry",
                stylers: [{color: "#f5f5f5"}, {lightness: 21}]
            }, {
                featureType: "poi.park",
                elementType: "geometry",
                stylers: [{color: "#dedede"}, {lightness: 21}]
            }, {
                elementType: "labels.text.stroke",
                stylers: [{visibility: "on"}, {color: "#ffffff"}, {lightness: 16}]
            }, {
                elementType: "labels.text.fill",
                stylers: [{saturation: 36}, {color: "#333333"}, {lightness: 40}]
            }, {elementType: "labels.icon", stylers: [{visibility: "off"}]}, {
                featureType: "transit",
                elementType: "geometry",
                stylers: [{color: "#f2f2f2"}, {lightness: 19}]
            }, {
                featureType: "administrative",
                elementType: "geometry.fill",
                stylers: [{color: "#fefefe"}, {lightness: 20}]
            }, {
                featureType: "administrative",
                elementType: "geometry.stroke",
                stylers: [{color: "#fefefe"}, {lightness: 17}, {weight: 1.2}]
            }])
        }), t.createWithStyle("#dark", [{
            featureType: "all",
            elementType: "labels",
            stylers: [{visibility: "on"}]
        }, {
            featureType: "all",
            elementType: "labels.text.fill",
            stylers: [{saturation: 36}, {color: "#000000"}, {lightness: 40}]
        }, {
            featureType: "all",
            elementType: "labels.text.stroke",
            stylers: [{visibility: "on"}, {color: "#000000"}, {lightness: 16}]
        }, {
            featureType: "all",
            elementType: "labels.icon",
            stylers: [{visibility: "off"}]
        }, {
            featureType: "administrative",
            elementType: "geometry.fill",
            stylers: [{color: "#000000"}, {lightness: 20}]
        }, {
            featureType: "administrative",
            elementType: "geometry.stroke",
            stylers: [{color: "#000000"}, {lightness: 17}, {weight: 1.2}]
        }, {
            featureType: "administrative.country",
            elementType: "labels.text.fill",
            stylers: [{color: "#e5c163"}]
        }, {
            featureType: "administrative.locality",
            elementType: "labels.text.fill",
            stylers: [{color: "#c4c4c4"}]
        }, {
            featureType: "administrative.neighborhood",
            elementType: "labels.text.fill",
            stylers: [{color: "#e5c163"}]
        }, {
            featureType: "landscape",
            elementType: "geometry",
            stylers: [{color: "#000000"}, {lightness: 20}]
        }, {
            featureType: "poi",
            elementType: "geometry",
            stylers: [{color: "#000000"}, {lightness: 21}, {visibility: "on"}]
        }, {
            featureType: "poi.business",
            elementType: "geometry",
            stylers: [{visibility: "on"}]
        }, {
            featureType: "road.highway",
            elementType: "geometry.fill",
            stylers: [{color: "#e5c163"}, {lightness: "0"}]
        }, {
            featureType: "road.highway",
            elementType: "geometry.stroke",
            stylers: [{visibility: "off"}]
        }, {
            featureType: "road.highway",
            elementType: "labels.text.fill",
            stylers: [{color: "#ffffff"}]
        }, {
            featureType: "road.highway",
            elementType: "labels.text.stroke",
            stylers: [{color: "#e5c163"}]
        }, {
            featureType: "road.arterial",
            elementType: "geometry",
            stylers: [{color: "#000000"}, {lightness: 18}]
        }, {
            featureType: "road.arterial",
            elementType: "geometry.fill",
            stylers: [{color: "#575757"}]
        }, {
            featureType: "road.arterial",
            elementType: "labels.text.fill",
            stylers: [{color: "#ffffff"}]
        }, {
            featureType: "road.arterial",
            elementType: "labels.text.stroke",
            stylers: [{color: "#2c2c2c"}]
        }, {
            featureType: "road.local",
            elementType: "geometry",
            stylers: [{color: "#000000"}, {lightness: 16}]
        }, {
            featureType: "road.local",
            elementType: "labels.text.fill",
            stylers: [{color: "#999999"}]
        }, {
            featureType: "transit",
            elementType: "geometry",
            stylers: [{color: "#000000"}, {lightness: 19}]
        }, {featureType: "water", elementType: "geometry", stylers: [{color: "#000000"}, {lightness: 17}]}])
    }, e.GoogleMap = new t, e.GoogleMap.Constructor = t
}(window.jQuery), function (e) {
    "use strict";
    e.GoogleMap.init()
}(window.jQuery);