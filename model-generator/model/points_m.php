<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Points_m extends MY_Model {
	
	protected $table = 'points';
	
	function __construct() {
		parent :: __construct();
	}
}
