import React, { Component } from 'react';
import PropTypes from 'prop-types';

class HomeForm extends Component {
  state = {
    value: ''
  };

  handleChange = (event) => {
    this.setState({
      value: event.target.value
    });
  };

  handleSubmit = (event) => {
    event.preventDefault();

    this.props.onSubmit(this.state.value);
  };

  render () {
    const { value } = this.state;

    return (
      <form onSubmit={(event) => this.handleSubmit(event)}>
        <input
          type='text'
          placeholder="What's your user name?"
          value={value}
          onChange={(event) => this.handleChange(event)}
        />
        &nbsp;
        <button>Sign In</button>
      </form>
    );
  }
}

HomeForm.propTypes = {
  onSubmit: PropTypes.func.isRequired
};

export default HomeForm;
