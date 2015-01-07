<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class History_m extends MY_Model {
	
	protected $table = 'history';
	
	function __construct() {
		parent :: __construct();
	}
}
