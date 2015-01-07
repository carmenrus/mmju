<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Message_m extends MY_Model {
	
	protected $table = 'message';
	
	function __construct() {
		parent :: __construct();
	}
}
