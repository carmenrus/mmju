<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Transfer_echarge_m extends MY_Model {
	
	protected $table = 'transfer_echarge';
	
	function __construct() {
		parent :: __construct();
	}
}
