import React from 'react';
import { Link } from 'react-router-dom';
import PropTypes from 'prop-types';

const IdeasList = ({ ideas }) => (
  <ul>
    {ideas.map((idea, index) => (
      <li key={index}>
        <Link to={`/ideas/${idea.slug}`}>{idea.title}</Link>
        {` (${idea.voters.length} votes)`}
      </li>))
    }
  </ul>
);

IdeasList.propTypes = {
  ideas: PropTypes.array.isRequired
};

export default IdeasList;
