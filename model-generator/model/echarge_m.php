<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Echarge_m extends MY_Model {
	
	protected $table = 'echarge';
	
	function __construct() {
		parent :: __construct();
	}
}
