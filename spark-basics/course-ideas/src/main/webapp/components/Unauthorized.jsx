import React from 'react';
import { Link } from 'react-router-dom';

const Unauthorized = () => (
  <div className='page'>
    <h1>Sorry</h1>
    <p>
      Please
      &nbsp;
      <Link to='/'>log in</Link>
      &nbsp;
      to view this page.
    </p>
  </div>
);

export default Unauthorized;
