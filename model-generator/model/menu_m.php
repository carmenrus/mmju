<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Menu_m extends MY_Model {
	
	protected $table = 'menu';
	
	function __construct() {
		parent :: __construct();
	}
}
