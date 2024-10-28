// VideoStreaming.tsx
import React from 'react';
import { View, Text, StyleSheet } from 'react-native';
import Video from 'react-native-video';

const VideoStreaming = ({ stream, name }) => {
  return (
    <View style={styles.videoStreamingWindow}>
      <Video
        source={{ uri: stream.toURL() }}
        style={styles.videoStreaming}
        resizeMode="cover"
        repeat
        controls={true}
      />
      <Text style={styles.videoStreamingName}>{name}</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  videoStreamingWindow: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: 'red',
    width: '100%',
    height: '300px',
  },
  videoStreaming: {
    width: '100%',
    height: '100%',
    backgroundColor: 'blue'
  },
  videoStreamingName: {
    marginTop: 8,
    textAlign: 'center',
  },
});

export default VideoStreaming;
