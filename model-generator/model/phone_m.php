<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Phone_m extends MY_Model {
	
	protected $table = 'phone';
	
	function __construct() {
		parent :: __construct();
	}
}
