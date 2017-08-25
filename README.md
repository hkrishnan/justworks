# Justworks

A Leiningen template for projects on Pedestal and GraphQL (Lacinia) on `components`.

## Table of Contents

* [Getting Started](#getting-started)
* [More Details](#more-details)
* [Bugs](#bugs)
* [Help!](#help)

## Getting Started

Create a new justworks project with:

    $ lein new justworks my-server

`my-server` will be the name of your project. When done, run:

    $ cd my-server/
    $ lein run

You should see the server initializing and ready:

```
[main] INFO org.eclipse.jetty.server.Server - jetty-9.4.0.v20161208
[main] INFO org.eclipse.jetty.server.handler.ContextHandler - Started o.e.j.s.ServletContextHandler@6e892437{/,null,AVAILABLE}
[main] INFO org.eclipse.jetty.server.AbstractConnector - Started ServerConnector@5a208e17{HTTP/1.1,[http/1.1, h2c]}{0.0.0.0:3000}
[main] INFO org.eclipse.jetty.server.Server - Started @17352ms
```

Open another terminal and fire a simple query against the server:

    $ curl localhost:8888/graphql -X POST -H "content-type: application/graphql" -d '{ hello }'

You should see:

    {"data":{"hello":"... world!!!!"}}

Point your browser to [http://localhost:3000](http://localhost:3000) and you will see graphiQL's interface.

## More Details

Refer to the `README.md` of your project for more information on how to use the template.

## Bugs

If you find a bug, submit a [Github issue](https://github.com/workco/justworks/issues).

## Help

This project is looking for team members who can help this project succeed!
If you are interested in becoming a team member please open an issue.

## License

Copyright © 2017 Tiago Luchini

Distributed under the MIT License.
