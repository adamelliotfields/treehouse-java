import React, { Component } from 'react';
import PropTypes from 'prop-types';
import axios from 'axios';

import IdeaVotersList from './IdeaVotersList.jsx';
import NotFound from './NotFound.jsx';

class Idea extends Component {
  state = {
    isReady: false,
    hasError: false,
    data: null
  };

  handleClick = async () => {
    try {
      const response = await axios.post(`http://localhost:4567/api/ideas/${this.props.match.params.slug}/vote`);

      this.setState({
        data: response.data
      });
    } catch (error) {
      this.setState({
        error: true
      });
    }
  };

  async componentDidMount () {
    try {
      const response = await axios.get(`http://localhost:4567/api/ideas/${this.props.match.params.slug}`);

      this.setState({
        isReady: true,
        data: response.data
      });
    } catch (error) {
      this.setState({
        isReady: true,
        hasError: true
      });
    }
  }

  render () {
    const { isReady, hasError, data } = this.state;

    if (!isReady) return null;

    if (hasError) return <NotFound />;

    return (
      <div className='page'>
        <h1>{data.idea.title}</h1>
        <h2>{`${data.idea.voters.length} voters`}</h2>
        <IdeaVotersList voters={data.idea.voters} />
        <button onClick={this.handleClick}>Vote</button>
      </div>
    );
  }
}

Idea.propTypes = {
  match: PropTypes.object.isRequired
};

export default Idea;
