import React from 'react';
import './App.css';
import ImageGallery from 'react-image-gallery';
import 'react-image-gallery/styles/css/image-gallery.css';

// Import your images
import tulip1 from './images/tulip1.jpeg';
import tulip2 from './images/tulip2.jpeg';
import tulip3 from './images/tulip3.jpeg';
import tulip4 from './images/tulip4.jpeg';
import tulip5 from './images/tulip5.jpeg';
import tulip6 from './images/tulip6.jpeg';

function App() {
  // Create a state variable for your images
  const [images] = React.useState([
    {
      original: tulip1,
      thumbnail: tulip1,
    },
    {
      original: tulip2,
      thumbnail: tulip2,
    },
    {
      original: tulip3,
      thumbnail: tulip3,
    },
    {
      original: tulip4,
      thumbnail: tulip4,
    },
    {
      original: tulip5,
      thumbnail: tulip5,
    },
    {
      original: tulip6,
      thumbnail: tulip6,
    },
  ]);

  return (
    <div className="App">
      <header className="App-header">
        <ImageGallery items={images} />
      </header>
    </div>
  );
}

export default App;