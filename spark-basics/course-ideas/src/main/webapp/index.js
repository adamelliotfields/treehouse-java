import React from 'react';
import { render } from 'react-dom';
import { BrowserRouter, Switch, Route } from 'react-router-dom';

import Home from './components/Home.jsx';
import Ideas from './components/Ideas.jsx';
import Idea from './components/Idea.jsx';
import NotFound from './components/NotFound.jsx';

render(
  <BrowserRouter>
    <Switch>
      <Route exact path='/' component={Home} />
      <Route exact path='/ideas' component={Ideas} />
      <Route path='/ideas/:slug' component={Idea} />
      <Route component={NotFound} />
    </Switch>
  </BrowserRouter>,
  document.getElementById('root')
);
