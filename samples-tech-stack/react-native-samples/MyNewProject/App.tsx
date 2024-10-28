import React, { useEffect, useState } from 'react';

import { View, StyleSheet, Button } from 'react-native';
import { mediaDevices, MediaStream, RTCView } from 'react-native-webrtc';

const App = () => {
  const [stream, setStream] = useState<MediaStream | null>(null);

  const startStream = async () => {
    try {
      const stream = await mediaDevices.getUserMedia({
        video: true,
        audio: true,
      });
      setStream(stream);
    } catch (error) {
      console.error('Error accessing media devices:', error);
    }
  };

  useEffect(() => {
    startStream();

    return () => {
      stream?.getTracks().forEach((track) => track.stop());
    };
  }, []);

  return (
    <View style={styles.container}>
      <Button title="Start Stream" onPress={startStream} />
      {stream && (
        <RTCView
          streamURL={stream.toURL()}
          style={styles.rtcView}
        />
      )}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#fff',
  },
  rtcView: {
    width: '100%',
    height: '100%',
  },
});

export default App;