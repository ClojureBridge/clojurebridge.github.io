[![Build Status](https://travis-ci.com/ClojureBridge/clojurebridge.github.io.svg?branch=master)](https://travis-ci.com/ClojureBridge/clojurebridge.github.io)

# ClojureBridge Website

Welcome! This is the repository for the [clojurebridge.org](http://www.clojurebridge.org/) website.
We are happy to receive your contributions.

## How to get started
You can either create and modify files here on GitHub or clone the repository and view the website on your computer.

The website is powered by [Jekyll](https://jekyllrb.com/), GitHub pages and Markdown. Jekyll is a Ruby gem that lets you create simple blogs that are easy to maintain.

You might have to [install a new version of Ruby](https://www.ruby-lang.org/en/documentation/installation/), if you don't have it yet.
After that you can run the following command in the Terminal:
```
gem install jekyll
```
Clone the repository, `cd` into it, pull the latest changes and enter `bundle install` in your Terminal. Once you are done with that you can run Jekyll locally with `bundle exec jekyll serve` and see it running in your browser at http://localhost:4000/.

If you run into problems with bundler/nokogiri etc. please checkout this [PR thread](https://github.com/ClojureBridge/website/pull/12#issuecomment-250129774).

## Workshops
Jekyll has `posts` and we decided to make a workshop a `post` for convenience. Every `post` has its own markdown file. So just head over, create a new workshop `post` and send us a pull request.

## Issues
We collect issues about things we still need to do. Please let us know in the issue if you want to start working on it.

## Contact
If you have any questions please don't hesitate to contact us at <info@clojurebridge.org>.
