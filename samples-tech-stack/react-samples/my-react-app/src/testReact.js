import React, { useState, useEffect } from 'react';

const TestReact = () => {
    const [data, setData] = useState([]);

    useEffect(() => {
        // Fetch the sample JSON data
        fetch('sample.json')
            .then(response => response.json())
            .then(data => setData(data))
            .catch(error => console.log(error));
    }, []);

    return (
        <div>
            {data.map(item => (
                <div key={item.id}>
                    <h2>{item.title}</h2>
                    <p>{item.description}</p>
                </div>
            ))}
        </div>
    );
};

export default TestReact;
