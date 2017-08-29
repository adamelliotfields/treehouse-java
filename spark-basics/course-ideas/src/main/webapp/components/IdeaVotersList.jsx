import React from 'react';
import PropTypes from 'prop-types';

const IdeaVotersList = ({ voters }) => (
  <ul>
    {voters.map((item, index) => (
      <li key={index}>{item}</li>
    ))}
  </ul>
);

IdeaVotersList.propTypes = {
  voters: PropTypes.array.isRequired
};

export default IdeaVotersList;
