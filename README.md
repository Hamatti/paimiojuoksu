<h1> Scores system for a running competition </h1>

<h2>Background</h2>

Last 10 years I have been involved in a local running competition in my hometown, Paimio, and we've always used a multi-worksheet Excel which has been somewhat useful but a pain in the ass when trying to get the result sheets to a web page and still have them looking nice.

So for this year's competition, I decided to make a completely new, easy-to-use stand-alone Java software which makes it easy to generate HTML files for both printing and also for web pages.

April 28th 2013 is the first time the software is used so further improvements will follow after that.

<h2>Features</h2>

The software allows user to deal with multiple series with number ranges (like Men 40 yrs would have numbers 101-140 etc) as well as handling exceptions with numbers (number 115 from the previous range would be accidentally given to someone in Ladies 25 yrs serie). 

<ol>
	<li> Create a new competition </li>
	<li> Read series' ranges from files </li>
	<li> a) add competitors one at the time or b) read runners from external file </li>
	<li> Add result per runner by their number </li>
	<li> Generate HTML files </li>
</ol>

<h2> Contact </h2>

If you have any ideas to improve the code, feel free to fork and make a pull request to contribute. Or you can contact me via Twitter @hamatti.

<h2> Licence </h2>

Copyright (C) 2013 Juha-Matti Santala

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.