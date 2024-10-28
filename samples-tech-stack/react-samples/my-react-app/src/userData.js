// move userData to a function
const getUserData = () => {
    return {
        "users": [
            {
                "id": 1,
                "name": "John Doe",
                "email": "john.doe@example.com"
            },
            {
                "id": 2,
                "name": "Jane Doe",
                "email": "jane.doe@example.com"
            }
        ]
    };
};

export default getUserData;