import { RTCPeerConnection } from 'react-native-webrtc';

    export function createPeerConnection() {
        let configuration: RTCConfiguration = { 'iceServers': [{ 'urls': this.TURN_SERVER_URLS }] };

        console.log('Creating peer connection');
        this.pc = new RTCPeerConnection(configuration);

        console.log('Created peer connection');

        if (this.pc === null || this.pc === undefined )
        return "failed to create webrtc";

        return "hello connection is done";
     }