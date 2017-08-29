import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

import HomeForm from './HomeForm.jsx';

// Display Hello ${username}! if the user is logged in
// Hide the link if the user is not logged in
class Home extends Component {
  state = {
    isReady: false,
    data: null
  };

  handleSubmit = async (username) => {
    const params = new window.URLSearchParams();
    params.append('username', username);

    const response = await axios.post('http://localhost:4567/api/login', params);

    this.setState({
      data: response.data
    });
  };

  async componentDidMount () {
    const response = await axios.get('http://localhost:4567/api/login', { withCredentials: true });

    this.setState({
      isReady: true,
      data: response.data.username ? response.data : null
    });
  }

  render () {
    const { isReady, data } = this.state;

    if (!isReady) return null;

    return (
      <div className='page'>
        {data ? <h1>{`Hello ${data.username}`}</h1> : <h1>Login</h1>}
        {data
          ? <p>View all course&nbsp;<Link to='/ideas' children='ideas' />.</p>
          : null}
        <HomeForm onSubmit={this.handleSubmit} />
      </div>
    );
  }
}

export default Home;
