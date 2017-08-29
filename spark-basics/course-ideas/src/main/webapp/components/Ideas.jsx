import React, { Component } from 'react';
import axios from 'axios';

import IdeasList from './IdeasList.jsx';
import IdeasForm from './IdeasForm.jsx';
import Unauthorized from './Unauthorized.jsx';

// Loop over ideas list and render the title value for each idea
// The slug URL needs to be generated from the slug property
class Ideas extends Component {
  state = {
    isReady: false,
    isAuthorized: false,
    data: null
  };

  handleSubmit = async (title) => {
    const params = new window.URLSearchParams();
    params.append('title', title);

    const response = await axios.post('http://localhost:4567/api/ideas', params);

    this.setState({
      data: response.data
    });
  };

  async componentDidMount () {
    try {
      const response = await axios.get('http://localhost:4567/api/ideas', { withCredentials: true });

      this.setState({
        isReady: true,
        isAuthorized: true,
        data: response.data
      });
    } catch (error) {
      this.setState({
        isReady: true,
        isAuthorized: false
      });
    }
  }

  render () {
    const { isReady, isAuthorized, data } = this.state;

    if (!isReady) return null;

    if (!isAuthorized) return <Unauthorized />;

    return (
      <div className='page'>
        <h1>Current Ideas</h1>
        <IdeasList ideas={data.ideas} />
        <IdeasForm
          onSubmit={this.handleSubmit}
        />
      </div>
    );
  }
}

export default Ideas;
