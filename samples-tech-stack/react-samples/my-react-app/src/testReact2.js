import React from 'react';
import getUserData from './userData';

const UserList = () => {
    return (
        <div>
            {getUserData().users.map((user, index) => (
                <div key={index}>
                    <h2>{user.name}</h2>
                    <p>{user.email}</p>
                </div>
            ))}
        </div>
    );
};

export default UserList;